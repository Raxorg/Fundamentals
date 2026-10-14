#ifdef GL_ES
#define PRECISION mediump
precision PRECISION float;
#else
#define PRECISION
#endif

varying vec2 v_texCoords;

uniform PRECISION sampler2D u_texture0;
uniform vec2 u_texel;
uniform float u_offset;

void main() {
    vec2 diagonalOffset = u_texel * u_offset;

    vec4 sum = texture2D(u_texture0, v_texCoords) * 4.0;
    sum += texture2D(u_texture0, v_texCoords + vec2(-diagonalOffset.x, -diagonalOffset.y));
    sum += texture2D(u_texture0, v_texCoords + vec2(diagonalOffset.x, -diagonalOffset.y));
    sum += texture2D(u_texture0, v_texCoords + vec2(-diagonalOffset.x, diagonalOffset.y));
    sum += texture2D(u_texture0, v_texCoords + vec2(diagonalOffset.x, diagonalOffset.y));

    gl_FragColor = sum * 0.125;
}