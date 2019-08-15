/***
 * Excerpted from "OpenGL ES for Android",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/kbogla for more book information.
***/
package com.example.demo2.programs;

import static android.opengl.GLES20.glUseProgram;
import android.content.Context;
import com.example.demo2.util.ShaderHelper;
import com.example.demo2.util.TextResourceReader;


abstract class ShaderProgram {
    // Uniform constants
    protected static final String U_MATRIX = "u_Matrix";
    protected static final String U_TEXTURE_UNIT = "u_TextureUnit";


    // Attribute constants
    protected static final String A_POSITION = "a_Position";
    protected static final String A_COLOR = "a_Color";
    protected static final String A_TEXTURE_COORDINATES = "a_TextureCoordinates";

    //第二张纹理
    protected static final String U_TEXTURE_UNIT2 = "u_TextureUnit2";
    protected static final String A_TEXTURE_COORDINATES2 = "a_TextureCoordinates2";

    // Shader program
    protected final int program;

    /**
     * 编译顶点与片段着色器，并连接到opengl程序中
     * @param context
     * @param vertexShaderResourceId
     * @param fragmentShaderResourceId
     */
    protected ShaderProgram(Context context, int vertexShaderResourceId,
        int fragmentShaderResourceId) {
        // Compile the shaders and link the program.
        program = ShaderHelper.buildProgram(
            TextResourceReader.readTextFileFromResource(context, vertexShaderResourceId),
            TextResourceReader.readTextFileFromResource(context, fragmentShaderResourceId)
        );
    }

    /**
     * 使用openGl程序
     */
    public void useProgram() {
        // Set the current OpenGL shader program to this program.
        glUseProgram(program);
    }
}
