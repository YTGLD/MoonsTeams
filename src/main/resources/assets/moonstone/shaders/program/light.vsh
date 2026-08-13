#version 150
#extension GL_ARB_explicit_attrib_location : enable

layout(location = 0) in vec3 aPos; // 顶点位置
layout(location = 1) in vec3 aNormal; // 顶点法线

out vec3 FragPos;
out vec3 Normal;

uniform mat4 model;
uniform mat4 view;
uniform mat4 projection;

void main()
{
    FragPos = vec3(model * vec4(aPos, 1.0));
    Normal = mat3(transpose(inverse(model))) * aNormal; // 对法线进行变换以防止变形
    gl_Position = projection * view * vec4(FragPos, 1.0);
}
