#version 150

out vec4 fragColor;

in vec3 FragPos; // 片段位置
in vec3 Normal;  // 片段法线

uniform float lightPos_X; // 光源位置X
uniform float lightPos_Y; // 光源位置Y
uniform float lightPos_Z; // 光源位置Z

uniform vec3 lightColor; // 光源颜色

void main()
{

    vec3 lightPos = vec3(lightPos_X, lightPos_Y, lightPos_Z);
    vec3 lightDir = normalize(lightPos - FragPos); // 计算光源方向

    // 简单的点光源光照计算
    float diff = max(dot(Normal, lightDir), 0.0); // 计算漫反射强度
    vec3 diffuse = diff * lightColor; // 计算漫反射颜色

    // 如果你想创建一个光团的效果，可以使用FragPos和lightPos之间的距离来控制亮度
    float distance = length(lightPos - FragPos);
    float radius = 1.0; // 光团的半径
    float attenuation = max(0.0, 1.0 - pow(distance / radius, 2.0)); // 计算衰减

    vec3 light = attenuation * diffuse; // 应用衰减

    fragColor = vec4(light, 1.0); // 输出颜色
}
