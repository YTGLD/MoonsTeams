#version 150


uniform float GameTime;
uniform sampler2D DiffuseSampler;

in vec2 texCoord;
in vec2 sampleStep;

out vec4 fragColor;


void main() {
    float sigma = 10.0;
    float rcpFactor = 1.0 / (sqrt(2.0 * 3.141592653589793) * sigma);

    vec4 blurred = vec4(0.0);
    float weightSum = 0.0;
    float radius = 8.0;
    for (float a = -radius; a <= radius; a += 1) {
        float weight = rcpFactor * exp(-(a * a) / (2.0 * sigma * sigma));
        // 动态调整纹理坐标以实现扭曲效果
        vec2 adjustedTexCoord = texCoord + sampleStep * a + vec2(sin(GameTime + texCoord.x * 10.0), cos(GameTime + texCoord.y * 10.0)) * 0.02;
        blurred += texture(DiffuseSampler, adjustedTexCoord) * weight;
        weightSum += weight;
    }

    // 对边缘点进行特殊处理，同样引入时间变量
    vec2 adjustedTexCoord = texCoord + sampleStep * radius + vec2(sin(GameTime + texCoord.x * 10.0), cos(GameTime + texCoord.y * 10.0)) * 0.05;
    blurred += texture(DiffuseSampler, adjustedTexCoord) * rcpFactor * exp(-(radius * radius) / (2.0 * sigma * sigma)) / 2.0;
    weightSum += rcpFactor * exp(-(radius * radius) / (2.0 * sigma * sigma)) / 2.0;

    float aaa = blurred.a * 500;
    float r = (blurred / weightSum).r * 1.25;
    float g = (blurred / weightSum).g * 1.25;
    float b = (blurred / weightSum).b * 1.25;
    fragColor = vec4(vec3(r, g, b), aaa);
}

