uniform mat4 u_Matrix;
attribute vec4 a_Position;
attribute vec2 a_TextureCoordinates;

varying vec2 v_TextureCoordinates; //顶点有2个分量，即s 与 t分量


//多生纹理的对象
attribute vec2 a_TextureCoordinates2;
varying vec2 v_TextureCoordinates2;

void main(){
    v_TextureCoordinates = a_TextureCoordinates;
    gl_Position = u_Matrix * a_Position;

    //多重纹理的属性
    v_TextureCoordinates2 = a_TextureCoordinates2;
    gl_PointSize = 10.0;
}