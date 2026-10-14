#ifdef GL_ES
precision mediump float;
#endif

varying vec2 v_texCoords;

uniform sampler2D u_texture;
uniform float u_tintAmount;

void main() {
    vec4 color = texture2D(u_texture, v_texCoords);
    float tint = clamp(u_tintAmount, 0.0, 1.0);
    gl_FragColor = vec4(mix(color.rgb, vec3(1.0, 0.0, 0.0), tint), color.a);
}
