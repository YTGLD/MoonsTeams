#version 150

uniform sampler2D Sampler0;

in vec4 v_Color;
in vec2 v_TexCoord;

out vec4 fragColor;

void main() {
    vec4 color = texture(Sampler0, v_TexCoord) * v_Color;
    fragColor = color;
}