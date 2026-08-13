#version 150

uniform sampler2D DiffuseSampler;

in vec2 texCoord;
in vec2 oneTexel;

out vec4 fragColor;

void main(){
    vec4 center = texture(DiffuseSampler, texCoord);
    fragColor = center;
}
