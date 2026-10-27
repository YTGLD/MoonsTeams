#version 330

#moj_import <minecraft:globals.glsl>



layout(std140) uniform DynamicTransforms {
    mat4 ModelViewMat;
    vec4 ColorModulator;
    vec3 ModelOffset;
    mat4 TextureMat;
};

uniform sampler2D Sampler0;

in vec2 texCoord0;
in vec4 vertexColor;

out vec4 fragColor;

void main() {
    vec2 uv = texCoord0;

    float wave1 = sin(uv.y * 15.0 + GameTime * 1600) * 0.02;
    float wave2 = cos(uv.x * 20.0 + GameTime * 1600 * 1.3) * 0.015;
    float wave3 = sin((uv.x + uv.y) * 30.0 + GameTime * 1600 * 0.7) * 0.01;

    vec2 offset = vec2(wave1 + wave3, wave2 + wave3);

    // 扭曲后的纹理坐标
    vec2 deformedUV = uv + offset;
    // 防止纹理越界
    deformedUV = clamp(deformedUV, 0.0, 1.0);

    // 采样纹理
    vec4 color = texture(Sampler0, deformedUV) * vertexColor;

    if (color.a <= 0.0) discard;
    fragColor = color;
}