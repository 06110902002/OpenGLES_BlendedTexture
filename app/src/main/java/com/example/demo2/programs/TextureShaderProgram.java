/***
 * Excerpted from "OpenGL ES for Android",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/kbogla for more book information.
***/
package com.example.demo2.programs;

import android.content.Context;
import com.example.demo2.R;

import static android.opengl.GLES20.*;


public class TextureShaderProgram extends ShaderProgram {
    // Uniform locations
    private final int uMatrixLocation;
    private final int uTextureUnitLocation;
    
    // Attribute locations
    private final int aPositionLocation;
    private final int aTextureCoordinatesLocation;


    private int uTextureUnit2Location;
    private int aTextureCoordinatesLocation2;

    public TextureShaderProgram(Context context) {
        super(context, R.raw.texture_vertex_shader,
            R.raw.texture_fragment_shader);

        // Retrieve uniform locations for the shader program.
        uMatrixLocation = glGetUniformLocation(program, U_MATRIX);
        uTextureUnitLocation = glGetUniformLocation(program, U_TEXTURE_UNIT);
        
        // Retrieve attribute locations for the shader program.
        aPositionLocation = glGetAttribLocation(program, A_POSITION);
        aTextureCoordinatesLocation = 
            glGetAttribLocation(program, A_TEXTURE_COORDINATES);


        //混合纹理的位置
        uTextureUnit2Location = glGetUniformLocation(program, U_TEXTURE_UNIT2);
        aTextureCoordinatesLocation2 = glGetAttribLocation(program, A_TEXTURE_COORDINATES2);

    }

    public void setUniforms(float[] matrix, int textureId) {
        // Pass the matrix into the shader program.
        glUniformMatrix4fv(uMatrixLocation, 1, false, matrix, 0);

        // Set the active texture unit to texture unit 0.
        glActiveTexture(GL_TEXTURE0);

        // Bind the texture to this unit.
        glBindTexture(GL_TEXTURE_2D, textureId);

        // Tell the texture uniform sampler to use this texture in the shader by
        // telling it to read from texture unit 0.
        glUniform1i(uTextureUnitLocation, 0);
    }

    /**
     * 激活混合纹理
     * @param matrix
     * @param textureId
     */
    public void setBlenTextureUniforms(float[] matrix,int textureId){

        //激活纹理2
        glUniformMatrix4fv(uMatrixLocation, 1, false, matrix, 0);
        glActiveTexture(GL_TEXTURE1);
        glBindTexture(GL_TEXTURE_2D, textureId);
        glUniform1i(uTextureUnit2Location, 1);
    }

    /**
     * 获取顶点的位置
     * @return
     */
    public int getPositionAttributeLocation() {
        return aPositionLocation;
    }

    /**
     * 获取纹理的位置
     * @return
     */
    public int getTextureCoordinatesAttributeLocation() {
        return aTextureCoordinatesLocation;
    }


    /**
     * 获取第二张纹理的位置
     * @return
     */
    public int getTextureCoordinatesAttributeLocation2() {
        return aTextureCoordinatesLocation2;
    }


}
