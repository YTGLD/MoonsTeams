#version 150

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
    for (float a = -radius; a <= radius; a +=1) {
        float weight = rcpFactor * exp(-(a * a) / (2.0 * sigma * sigma));
        blurred += texture(DiffuseSampler, texCoord + sampleStep * a) * weight;
        weightSum += weight;
    }

    blurred += texture(DiffuseSampler, texCoord + sampleStep * radius) * rcpFactor * exp(-(radius * radius) / (2.0 * sigma * sigma)) / 2.0;
    weightSum += rcpFactor * exp(-(radius * radius) / (2.0 * sigma * sigma)) / 2.0;

    float aaa = blurred.a * 500;
    float r = (blurred / weightSum).r * 2.55;
    float g = (blurred / weightSum).g * 2.55;
    float b = (blurred / weightSum).b * 2.55;
    fragColor = vec4(vec3(r,g,b),  aaa);
}

