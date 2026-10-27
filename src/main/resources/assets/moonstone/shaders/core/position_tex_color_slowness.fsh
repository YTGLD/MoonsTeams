#version 330

#moj_import <minecraft:globals.glsl>


uniform sampler2D Sampler0;

in vec2 texCoord0;
in vec4 vertexColor;

out vec4 fragColor;
void main() {
    vec2 center = vec2(0.5, 0.5);
    vec2 diff = texCoord0 - center;
    float distance = length(diff);

    //扭曲
    float twistFactor = sin((GameTime * 3333) + distance * 30.0 + offset) * stronger; // 扭曲因子

    float angle = atan(diff.y, diff.x);
    float newAngle = angle + twistFactor;
    vec2 deformedTexCoord = center + distance * vec2(cos(newAngle), sin(newAngle));

    vec4 color = texture(Sampler0, deformedTexCoord) * vertexColor;
    if (color.a <= 0.0) {
        discard;
    }

    color.rgb += light;
    fragColor = color ;
}