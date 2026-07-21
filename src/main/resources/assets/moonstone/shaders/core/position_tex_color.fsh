#version 330

uniform sampler2D Sampler0;

in vec2 texCoord0;
in vec4 vertexColor;
in vec3 PositionPosition;
out vec4 fragColor;

void main() {
    vec2 uv = texCoord0;

    float GameTime = texture(Sampler0, texCoord0).x + texture(Sampler0, texCoord0).y + texture(Sampler0, texCoord0).z;
    GameTime += vertexColor.a + vertexColor.r + vertexColor.g + vertexColor.b; GameTime += texCoord0.x + texCoord0.y;
    GameTime *= sin(PositionPosition.x + PositionPosition.y + PositionPosition.z);
    float wave1 = sin(uv.y * 15.0 + GameTime) * 0.02;
    float wave2 = cos(uv.x * 20.0 + GameTime * 1.3) * 0.015;
    float wave3 = sin((uv.x + uv.y) * 30.0 + GameTime * 0.7) * 0.01;

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