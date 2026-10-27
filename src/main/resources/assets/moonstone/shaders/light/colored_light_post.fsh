#version 330
#extension GL_ARB_separate_shader_objects : require

layout(location = 0) out vec4 fragColor;

layout(location = 1) in vec2 texCoord;


/*
 * ============================================================
 * Projection
 * ============================================================
 */

layout(std140) uniform Projection {
    mat4 ProjMat;
};


/*
 * ============================================================
 * Scene
 * ============================================================
 */

uniform sampler2D MainSampler;
uniform sampler2D DepthSampler;


/*
 * ============================================================
 * Colored Lights
 * ============================================================
 *
 * LightPositionRadius[i]
 *
 *     xyz = View Space 光源位置
 *     w   = 光源半径
 *
 *
 * LightColorIntensity[i]
 *
 *     rgb = 光源颜色
 *     a   = 光源强度
 */

layout(std140) uniform ColoredLights {

    vec4 LightPositionRadius[32];

    vec4 LightColorIntensity[32];

};


/*
 * ============================================================
 * View Space Position Reconstruction
 * ============================================================
 *
 * 根据：
 *
 *     UV
 *     Depth
 *
 * 还原当前像素对应的 View Space 位置。
 */

vec3 reconstructViewPosition(
    vec2 uv,
    float depth
) {

/*
     * --------------------------------------------------------
     * UV
     *
     * 0 ~ 1
     *
     * ↓
     *
     * -1 ~ 1
     * --------------------------------------------------------
     */

    vec2 ndc =
    uv * 2.0 - 1.0;


/*
     * --------------------------------------------------------
     * Clip Space
     *
     * 保持原来的 Minecraft Depth 处理方式。
     * --------------------------------------------------------
     */

    vec4 clipPosition =
    vec4(
    ndc,
    depth,
    1.0
    );


/*
     * --------------------------------------------------------
     * Clip Space
     *
     * ↓ inverse Projection
     *
     * View Space
     * --------------------------------------------------------
     */

    vec4 viewPosition =
    inverse(ProjMat) *
    clipPosition;


/*
     * --------------------------------------------------------
     * Perspective divide
     * --------------------------------------------------------
     */

    if (abs(viewPosition.w) > 0.000001) {

        viewPosition.xyz /=
        viewPosition.w;
    }


    return viewPosition.xyz;
}


/*
 * ============================================================
 * Luminance
 * ============================================================
 *
 * 计算颜色亮度。
 */

float luminance(
    vec3 color
) {

    return dot(
        color,
        vec3(
        0.2126,
        0.7152,
        0.0722
        )
    );
}


/*
 * ============================================================
 * Calculate One Colored Light
 * ============================================================
 */

vec3 calculateLight(
    vec3 surfacePosition,
    vec3 lightPosition,
    float radius,
    vec3 lightColor,
    float intensity
) {

/*
     * --------------------------------------------------------
     * Distance
     * --------------------------------------------------------
     */

    vec3 delta =
    lightPosition -
    surfacePosition;


    float distanceSquared =
    dot(
        delta,
        delta
    );


    float radiusSquared =
    radius *
    radius;


/*
     * 超出光源范围
     */

    if (distanceSquared >= radiusSquared) {

        return vec3(0.0);
    }


    float distance =
    sqrt(
        distanceSquared
    );


    float normalizedDistance =
    distance /
    radius;


/*
     * --------------------------------------------------------
     * Main Attenuation
     * --------------------------------------------------------
     *
     * 0 = 光源中心
     * 1 = 光源边缘
     */

    float attenuation =
    1.0 -
    smoothstep(
        0.0,
        1.0,
        normalizedDistance
    );


/*
     * 让中心更加明显
     */

    attenuation *=
    attenuation;


/*
     * --------------------------------------------------------
     * Core
     * --------------------------------------------------------
     *
     * 光源中心额外产生一个亮核。
     */

    float core =
    1.0 -
    smoothstep(
        0.0,
        0.45,
        normalizedDistance
    );


    core *= core;


/*
     * --------------------------------------------------------
     * Edge
     * --------------------------------------------------------
     *
     * 外围逐渐降低饱和度。
     *
     * 中心：
     *
     *     保持鲜艳
     *
     * 边缘：
     *
     *     更接近环境颜色
     */

    float edge =
    smoothstep(
        0.25,
        1.0,
        normalizedDistance
    );


    float lightLuminance =
    luminance(
        lightColor
    );


    vec3 desaturatedColor =
    mix(
        lightColor,
        vec3(lightLuminance),
        edge * 0.65
    );


/*
     * --------------------------------------------------------
     * Main Colored Light
     * --------------------------------------------------------
     */

    vec3 result =
    desaturatedColor *
    intensity *
    attenuation;


/*
     * --------------------------------------------------------
     * Core Color
     * --------------------------------------------------------
     *
     * 越靠近中心：
     *
     *     原始颜色
     *
     * ↓
     *
     *     白色
     *
     * 但不会完全变白。
     */

    vec3 coreColor =
    mix(
        lightColor,
        vec3(1.0),
        core * 0.35
    );


/*
     * Core 强度
     */

    result +=
    coreColor *
    core *
    intensity *
    0.35;


    return result;
}


/*
 * ============================================================
 * Calculate All Colored Lights
 * ============================================================
 *
 * 这里保留原来的多光源颜色混合。
 */

vec3 calculateColoredLights(
    vec3 surfacePosition
) {

    vec3 lighting =
    vec3(0.0);


    float totalWeight =
    0.0;


/*
     * 用于保留总体光强。
     */

    float totalIntensity =
    0.0;


    for (int i = 0; i < 32; i++) {

    /*
         * ----------------------------------------------------
         * Light Position
         * ----------------------------------------------------
         */

        vec3 lightPosition =
        LightPositionRadius[i].xyz;


        float radius =
        LightPositionRadius[i].w;


    /*
         * ----------------------------------------------------
         * Light Color
         * ----------------------------------------------------
         */

        vec3 lightColor =
        LightColorIntensity[i].xyz;


        float intensity =
        LightColorIntensity[i].w;


    /*
         * 无效光源
         */

        if (
        radius <= 0.0 ||
        intensity <= 0.0
        ) {

            continue;
        }


    /*
         * ----------------------------------------------------
         * Calculate contribution
         * ----------------------------------------------------
         */

        vec3 contribution =
        calculateLight(
            surfacePosition,
            lightPosition,
            radius,
            lightColor,
            intensity
        );


    /*
         * ----------------------------------------------------
         * Contribution brightness
         * ----------------------------------------------------
         */

        float brightness =
        luminance(
            contribution
        );


        if (brightness <= 0.00001) {

            continue;
        }


    /*
         * ----------------------------------------------------
         * Color Weight
         * ----------------------------------------------------
         *
         * 亮度越高，
         * 这个光源对最终颜色影响越大。
         */

        float weight =
        sqrt(
            brightness
        );


    /*
         * ----------------------------------------------------
         * Accumulate Color
         * ----------------------------------------------------
         */

        lighting +=
        contribution *
        weight;


        totalWeight +=
        weight;


    /*
         * 保存总体强度。
         */

        totalIntensity +=
        brightness;
    }


/*
     * --------------------------------------------------------
     * Normalize Color
     * --------------------------------------------------------
     */

    if (totalWeight > 0.00001) {

        lighting /=
        totalWeight;
    }


/*
     * --------------------------------------------------------
     * Restore Light Strength
     * --------------------------------------------------------
     */

    float strength =
    1.0 -
    exp(
        -totalIntensity * 1.35
    );


/*
     * 让光照不会过于暗。
     */

    strength =
    clamp(
        strength,
        0.0,
        1.0
    );


    lighting *=
    strength;


    return lighting;
}


/*
 * ============================================================
 * Soft Clamp
 * ============================================================
 */

vec3 softClamp(
    vec3 color
) {

    return
    color /
    (
    vec3(1.0) +
    color
    );
}


/*
 * ============================================================
 * Apply Colored Lighting
 * ============================================================
 *
 * 修改后的核心。
 *
 * 原来的方式：
 *
 *     sceneColor
 *         ↓
 *     彩色染色
 *         +
 *     additive glow
 *
 *
 * 现在：
 *
 *     原始像素
 *         ↓
 *     原始像素接受彩色光
 *
 *
 *     result =
 *
 *         sceneColor
 *         +
 *         sceneColor
 *         ×
 *         lightColor
 *         ×
 *         lightStrength
 *
 * ============================================================
 */
vec3 applyColoredLighting(vec3 sceneColor, vec3 lightColor) {
    float lightStrength =
    max(
        lightColor.r,
        max(lightColor.g, lightColor.b)
    );

    if (lightStrength <= 0.00001) {
        return sceneColor;
    }

    vec3 lightDirection = lightColor / lightStrength;

    // 提高彩色光照亮度
    float intensity = lightStrength * 2.5;

    vec3 result =
    sceneColor +
    sceneColor * lightDirection * intensity;

    // 保持高亮区域柔和
    vec3 extra = max(result - sceneColor, vec3(0.0));
    extra = softClamp(extra);

    result = sceneColor + extra;

    return clamp(result, 0.0, 1.0);
}


/*
 * ============================================================
 * Main
 * ============================================================
 */

void main() {

/*
     * --------------------------------------------------------
     * UV
     * --------------------------------------------------------
     */

    vec2 uv =
    texCoord;


/*
     * --------------------------------------------------------
     * Original Scene
     * --------------------------------------------------------
     */

    vec4 sceneColor =
    texture(
        MainSampler,
        uv
    );


/*
     * --------------------------------------------------------
     * Depth
     * --------------------------------------------------------
     */

    float depth =
    texture(
        DepthSampler,
        uv
    ).r;


/*
     * --------------------------------------------------------
     * Sky / No Geometry
     * --------------------------------------------------------
     *
     * 按照你原来的 Depth 判断方式保留。
     */

    if (depth <= 0.000001) {

        fragColor =
        sceneColor;

        return;
    }


/*
     * --------------------------------------------------------
     * Reconstruct Surface Position
     * --------------------------------------------------------
     */

    vec3 surfacePosition =
    reconstructViewPosition(
        uv,
        depth
    );


/*
     * --------------------------------------------------------
     * Colored Lights
     * --------------------------------------------------------
     */

    vec3 lightContribution =
    calculateColoredLights(
        surfacePosition
    );


/*
     * --------------------------------------------------------
     * Apply
     * --------------------------------------------------------
     */

    sceneColor.rgb =
    applyColoredLighting(
        sceneColor.rgb,
        lightContribution
    );


/*
     * --------------------------------------------------------
     * Output
     * --------------------------------------------------------
     */

    fragColor =
    sceneColor;
}