#version 150

in vec3 Position;
in vec4 Color;

uniform sampler2D Sampler0;
uniform sampler2D Sampler1;

out vec4 vertexColor;

uniform mat4 ModelViewMat;
uniform mat4 ProjMat;

void main() {
    gl_Position = ProjMat * ModelViewMat * vec4(Position, 1.0);
    vertexColor = Color;
}