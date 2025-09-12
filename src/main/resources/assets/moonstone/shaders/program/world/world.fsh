#version 150

#define CONST_EXP 2048
#define CONST_EXP2 2049
#define CONST_LINEAR 9729

uniform sampler2D DiffuseSampler;
uniform sampler2D s_diffuse_depth;

uniform mat4 u_INVMVP;


uniform float u_lightSources_position[96];
uniform float u_lightSources_color[96];
uniform float u_lightSources_radius[32];
uniform int u_lightSourcesAmount;

in vec2 v_texCoord;

//Output color
out vec4 o_fragColor;

//Calculates the fragment world position (relative to camera)
vec3 getFragPos(sampler2D depthMap) {
    float zBuffer = texture2D(depthMap, v_texCoord).r;
    float fragDepth = zBuffer * 2.0F - 1.0F;
    vec4 fragRelPos = vec4(v_texCoord.xy * 2.0F - 1.0F, fragDepth, 1.0F) * u_INVMVP;
    fragRelPos.xyz /= fragRelPos.w;
    return fragRelPos.xyz;
}

float getFogMultiplier(vec3 fragPos) {
    return 0.0F;
}

void main() {
    //Get fragment world position
    vec3 fragPos = getFragPos(s_diffuse_depth);

    //A color multiplier that is applied to the final color
    float colorMultiplier = 1.0F;

    //Strength of distortion
    float distortionMultiplier = 0.0F;

    //Holds the calculated color
    vec4 color = vec4(0.0F, 0.0F, 0.0F, 0.0F);

    //////// Lighting (Distortion) ////////
    //Calculate distance from fragment to light sources and apply color
    for (int i = 0; i < u_lightSourcesAmount; i++) {
        vec3 lightPos = vec3(u_lightSources_position[i * 3], u_lightSources_position[(i*3) + 1], u_lightSources_position[(i*3) + 2]);
        float dist = distance(lightPos, fragPos);
        float radius = u_lightSources_radius[i];
        if (dist < radius) {
            vec3 lightColor = vec3(u_lightSources_color[i * 3], u_lightSources_color[(i*3) + 1], u_lightSources_color[(i*3) + 2]);
            if (lightColor.r == -1 && lightColor.g == -1 && lightColor.b == -1) {
                if (distortionMultiplier < 0.6F) {
                    distortionMultiplier += max(distortionMultiplier, 1.0F - pow(dist / radius, 4));
                }
            }
        }
    }
    //////// Distortion and diffuse texel ////////
    vec4 sourceColor;
    if (distortionMultiplier <= 0.0F) {
        sourceColor = vec4(texture2D(DiffuseSampler, v_texCoord));
        color += sourceColor;
    } else {
        float fragDistortion = (fragPos.y + 0 + (cos(fragPos.x + 0) * sin(fragPos.z + 0))) * 5.0F;
        sourceColor = vec4(texture2D(DiffuseSampler, v_texCoord + vec2(sin(fragDistortion + 0 * 50.0F / 300.0F) / 800.0F, 0.0F) * distortionMultiplier));
        color += sourceColor;
    }

    //////// Lighting ////////
    //Calculate distance from fragment to light sources and apply color
    float lightingFogMultiplier = 1.0F - getFogMultiplier(fragPos);
    for (int i = 0; i < u_lightSourcesAmount; i++) {
        vec3 lightPos = vec3(u_lightSources_position[i * 3], u_lightSources_position[(i*3) + 1], u_lightSources_position[(i*3) + 2]);
        float dist = distance(lightPos, fragPos);
        float radius = u_lightSources_radius[i];
        vec3 lightColor = vec3(u_lightSources_color[i * 3], u_lightSources_color[(i*3) + 1], u_lightSources_color[(i*3) + 2]);

        if (dist < radius) {
            if (lightColor.r != -1 || lightColor.g != -1 || lightColor.b != -1) {
                color += (sourceColor * (vec4(lightColor * pow(1.0F - dist / radius, 2), 0.0F) * lightingFogMultiplier));
            }
        }
    }
    o_fragColor = color * colorMultiplier;
}