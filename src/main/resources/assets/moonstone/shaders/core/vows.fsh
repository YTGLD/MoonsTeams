#version 330
#extension GL_ARB_separate_shader_objects : require



layout(std140) uniform ChestVowsGlobals {
    float Time;
};

layout(std140) uniform DynamicTransforms {
    mat4 ModelViewMat;
    mat4 TextureMat;
    vec4 ColorModulator;
    vec3 ModelOffset;
};


uniform sampler2D Sampler0;

layout(location = 0) in vec2 texCoord0;
layout(location = 1) in vec4 vertexColor;

layout(location = 0) out vec4 fragColor;
void main() {
    float time = Time * 3.0;

    vec2 uv = texCoord0;

    float wave1 = sin(
        uv.y * 24.0 +
        time * 0.7
    );

    float wave2 = cos(
        uv.x * 30.0 -
        time * 0.55
    );

    uv.x += wave1 * 0.025;
    uv.y += wave2 * 0.025;

    float wave3 = sin(
        (uv.x + uv.y) * 35.0 +
        time * 1.2
    );

    uv += wave3 * 0.003;
    uv = clamp(
        uv,
        vec2(0.03),
        vec2(0.97)
    );
    vec4 color = texture(Sampler0, uv);

    vec3 rgb = color.rgb;
    float brightness = dot(
        rgb,
        vec3(
        0.299,
        0.587,
        0.114
        )
    );


    // =========================
    // 发光
    // =========================

    float glow = smoothstep(
        0.35,
        1.0,
        brightness
    );

    rgb += vec3(
    0.15,
    0.05,
    0.25
    ) * glow;
    float luminance = dot(
        rgb,
        vec3(
        0.299,
        0.587,
        0.114
        )
    );

    rgb = mix(
        vec3(luminance),
        rgb,
        1.15
    );

    float textureBrightness = dot(
        rgb,
        vec3(0.299, 0.587, 0.114)
    );
    vec3 baseColor = vertexColor.rgb;

    float colorWave =
    sin(
        uv.x * 8.0 +
        uv.y * 6.0 +
        time * 0.9
    ) * 0.5 + 0.5;

    float colorWave2 =
    sin(
        uv.y * 13.0 -
        uv.x * 5.0 -
        time * 0.6
    ) * 0.5 + 0.5;
    vec3 redColor = vec3(
    1.0,
    0.05,
    0.05
    );

    vec3 purpleColor = vec3(
    0.65,
    0.05,
    1.0
    );

    vec3 blueColor = vec3(
    0.05,
    0.25,
    1.0
    );
    vec3 rainbowColor;

    if (colorWave < 0.5) {

        rainbowColor = mix(
            redColor,
            purpleColor,
            colorWave * 2.0
        );

    } else {

        rainbowColor = mix(
            purpleColor,
            blueColor,
            (colorWave - 0.5) * 2.0
        );
    }

    rainbowColor = mix(
        rainbowColor,
        vec3(1.0, 0.1, 0.8),
        colorWave2 * 0.25
    );

    rgb *= baseColor;
    float highlight =
    smoothstep(
        0.25,
        1.0,
        textureBrightness
    );

    rgb +=
    rainbowColor *
    highlight *
    0.35;
    rgb = mix(
        rgb,
        rgb * rainbowColor * 1.5,
        0.25
    );
    float alpha =
    color.a *
    vertexColor.a;

    rgb *= ColorModulator.rgb;
    alpha *= ColorModulator.a;


    fragColor = vec4(
    rgb,
    alpha
    );
}