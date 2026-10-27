#version 330

uniform sampler2D InSampler;

layout(std140) uniform SamplerInfo {
    vec2 OutSize;
    vec2 InSize;
};

in vec2 texCoord;

out vec4 fragColor;

void main(){
    vec2 oneTexel = 1.0 / InSize;

    vec4 center = texture(InSampler, texCoord);
    fragColor = center;
}
