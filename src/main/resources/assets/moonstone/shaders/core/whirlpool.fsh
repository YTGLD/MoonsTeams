#version 330

#moj_import <minecraft:fog.glsl>
#moj_import <minecraft:matrix.glsl>
#moj_import <minecraft:globals.glsl>

uniform sampler2D Sampler0;

in vec2 texCoord0;
in vec4 vertexColor;

out vec4 fragColor;



void main() {

    vec2 center = vec2(0.5, 0.5);
    vec2 rotatedTexCoord = texCoord0 - center; // 将纹理坐标移到中心

    // 计算旋转矩阵
    float cosTheta = cos(GameTime*1001*speed);
    float sinTheta = sin(GameTime*1001*speed);
    mat2 rotationMatrix = mat2(cosTheta, -sinTheta, sinTheta, cosTheta);

    // 应用旋转矩阵
    rotatedTexCoord = rotationMatrix * rotatedTexCoord;

    // 将纹理坐标移回原来的位置
    rotatedTexCoord += center;

    vec4 color = texture(Sampler0, rotatedTexCoord) * vertexColor;
if (color.a <= 0.0) {
        discard;
    }
    fragColor = color;
}