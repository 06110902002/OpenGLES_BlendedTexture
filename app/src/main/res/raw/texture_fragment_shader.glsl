precision mediump float;

uniform sampler2D u_TextureUnit;
varying vec2 v_TextureCoordinates;


//多重纹理的对象
uniform sampler2D u_TextureUnit2;
varying vec2 v_TextureCoordinates2;

void main(){

    //单纹理
    //gl_FragColor = texture2D(u_TextureUnit,v_TextureCoordinates);

    //多纹理，，也可以相乘
    gl_FragColor = texture2D(u_TextureUnit,v_TextureCoordinates) + texture2D(u_TextureUnit2, v_TextureCoordinates2);
}