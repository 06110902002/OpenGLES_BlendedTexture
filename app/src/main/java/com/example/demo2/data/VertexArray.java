package com.example.demo2.data;

import com.example.demo2.constants.Constants;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import static android.opengl.GLES20.*;


public class VertexArray {

    private FloatBuffer floatBuffer;


    public VertexArray(float[] vertexData){
        floatBuffer = ByteBuffer
                .allocateDirect(vertexData.length * Constants.BYTES_PER_FLOAT)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer()
                .put(vertexData);
    }

    /**
     * 读取顶点数组到缓存区，供opengl 读取
     * @param dataOffset
     * @param attributeLocation
     * @param componentCount
     * @param stride
     */
    public void setVertexAttribPointer(int dataOffset,int attributeLocation,
                                       int componentCount,int stride){

        floatBuffer.position(dataOffset);


        glVertexAttribPointer(attributeLocation, componentCount, GL_FLOAT,
                false, stride, floatBuffer);

        glEnableVertexAttribArray(attributeLocation);

        floatBuffer.position(0);


    }
}
