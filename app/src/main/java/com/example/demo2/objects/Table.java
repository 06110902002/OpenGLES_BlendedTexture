package com.example.demo2.objects;

import android.opengl.GLES20;
import com.example.demo2.constants.Constants;
import com.example.demo2.data.VertexArray;
import com.example.demo2.programs.TextureShaderProgram;

import static android.opengl.GLES20.GL_TRIANGLE_FAN;

public class Table {

    private static final int POSITION_COMPONENT_COUNT = 2;
    private static final int TEXTURE_COORDINATES_COMPONENT_COUNT = 2;
    private static final int STRIDE = (POSITION_COMPONENT_COUNT
            + TEXTURE_COORDINATES_COMPONENT_COUNT) * Constants.BYTES_PER_FLOAT;

    private static final float[] VERTEX_DATA = {
            // Order of coordinates: X, Y, S, T

            // Triangle Fan 需要注意纹理坐标与顶点坐标的关系要一一对应
            0f,    0f, 0.5f, 0.5f,
            -0.5f, -0.8f,   0f, 0.9f,
            0.5f, -0.8f,   1f, 0.9f,
            0.5f,  0.8f,   1f, 0.1f,
            -0.5f,  0.8f,   0f, 0.1f,
            -0.5f, -0.8f,   0f, 0.9f };

    private final VertexArray vertexArray;

    public Table() {
        vertexArray = new VertexArray(VERTEX_DATA);
    }

    /**
     * 绑定顶点数组到着色器程序上
     * @param textureProgram
     */
    public void bindData(TextureShaderProgram textureProgram) {

        //传递顶点坐标
        vertexArray.setVertexAttribPointer(
                0,
                textureProgram.getPositionAttributeLocation(),
                POSITION_COMPONENT_COUNT,
                STRIDE);

        //传递纹理坐标
        vertexArray.setVertexAttribPointer(
                POSITION_COMPONENT_COUNT,
                textureProgram.getTextureCoordinatesAttributeLocation(),
                TEXTURE_COORDINATES_COMPONENT_COUNT,
                STRIDE);
    }

    public void bindBlened(TextureShaderProgram textureProgram){
        //传递顶点坐标
        vertexArray.setVertexAttribPointer(
                0,
                textureProgram.getPositionAttributeLocation(),
                POSITION_COMPONENT_COUNT,
                STRIDE);

        //传递纹理坐标
        vertexArray.setVertexAttribPointer(
                POSITION_COMPONENT_COUNT,
                textureProgram.getTextureCoordinatesAttributeLocation2(),
                TEXTURE_COORDINATES_COMPONENT_COUNT,
                STRIDE);
    }



    public void draw() {
        GLES20.glDrawArrays(GL_TRIANGLE_FAN, 0, 6);
    }
}
