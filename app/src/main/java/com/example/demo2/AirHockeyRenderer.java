/***
 * Excerpted from "OpenGL ES for Android",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/kbogla for more book information.
***/
package com.example.demo2;

import static android.opengl.GLES10.GL_TRIANGLE_FAN;
import static android.opengl.GLES20.*;
import static android.opengl.Matrix.*;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;


import com.example.demo2.objects.Mallet;
import com.example.demo2.objects.Table;
import com.example.demo2.programs.ColorShaderProgram;
import com.example.demo2.programs.TextureShaderProgram;
import com.example.demo2.util.*;

public class AirHockeyRenderer implements Renderer {
//    private static final String U_COLOR = "u_Color"; //需要shader里面的名称一致
//    private static final String A_POSITION = "a_Position";
//    private static final String A_COLOR = "a_Color";
//    private static final String U_MARTRIX = "u_Matrix";
//    private static final float[] projectionMatrix = new float[16];
//    private int uMatrixLocation;
//
//    //因为在二维平面中一个顶点有2个分量，所以就用以下变量进行保存
//    private static final int POSITION_COMPONENT_COUNT = 4;
//    private static final int COLOR_COMPONENT_COUNT = 3;
//    private static final int BYTES_PER_FLOAT = 4;
//    private static final int STRIDE =
//            (POSITION_COMPONENT_COUNT + COLOR_COMPONENT_COUNT) * BYTES_PER_FLOAT; //加入顶点颜色属性之后的步长
//
//
//    private  FloatBuffer vertexData;
//    private  Context context;
//    private int program;
//    private int uColorLocation;
//    private int aPositionLocation;
//    private int aColorLocation; //颜色位置，告诉opengl去哪读取的变量


    //方式二
    private final Context context;

    private final float[] projectionMatrix = new float[16];
    private final float[] modelMatrix = new float[16];

    private Table table;
    private Mallet mallet;

    private TextureShaderProgram textureProgram;
    private ColorShaderProgram colorProgram;

    private int texture;

    private int textureBlend;

//    public AirHockeyRenderer() {
//        // This constructor shouldn't be called -- only kept for showing
//        // evolution of the code in the chapter.
//        context = null;
//        vertexData = null;
//    }

    public AirHockeyRenderer(Context context) {
        this.context = context;
        
        /*
		float[] tableVertices = { 
			0f,  0f, 
			0f, 14f, 
			9f, 14f, 
			9f,  0f 
		};
         */

//		float[] tableVerticesWithTriangles = {
//			// Triangle 1
//			0f,  0f,
//			9f, 14f,
//			0f, 14f,
//
//			// Triangle 2
//			0f,  0f,
//			9f,  0f,
//			9f, 14f,
//			// Next block for formatting purposes
//			9f, 14f,
//			 // Comma here for formatting purposes
//
//			// Line 1
//			0f,  7f,
//			9f,  7f,
//
//			// Mallets
//			4.5f,  2f,
//			4.5f, 12f
//		};
//
//        float[] tableVerticesWithTriangles = {
//                // Order of coordinates: X, Y, Z, W, R, G, B
//
//                // Triangle Fan
//                0f,    0f, -8.0f, 1.5f,   1f,   1f,   1f,
//                -0.5f, -0.8f,-8.0f,   1f, 0.7f, 0.7f, 0.7f,
//                0.5f, -0.8f, -8.0f,   1f, 0.7f, 0.7f, 0.7f,
//                0.5f,  0.8f, -8.0f,   2f, 0.7f, 0.7f, 0.7f,
//                -0.5f,  0.8f, -8.0f,   2f, 0.7f, 0.7f, 0.7f,
//                -0.5f, -0.8f, -8.0f,   1f, 0.7f, 0.7f, 0.7f,
//
//                // Line 1
//                -0.5f, 0f, -8.0f, 1.5f, 1f, 0f, 0f,
//                0.5f, 0f, -8.0f, 1.5f, 1f, 0f, 0f,
//
//                // Mallets
//                0f, -0.4f, -8.0f, 1.25f, 0f, 0f, 1f,
//                0f,  0.4f, -8.0f, 1.75f, 1f, 0f, 0f
//        };
//
//
//        vertexData = ByteBuffer
//            .allocateDirect(tableVerticesWithTriangles.length * BYTES_PER_FLOAT)
//            .order(ByteOrder.nativeOrder())
//            .asFloatBuffer();
//
//        vertexData.put(tableVerticesWithTriangles);
    }


    @Override
    public void onSurfaceCreated(GL10 glUnused, EGLConfig config) {
        /*
		// Set the background clear color to red. The first component is red,
		// the second is green, the third is blue, and the last component is
		// alpha, which we don't use in this lesson.
		glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
         */

//        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
//
//        String vertexShaderSource = TextResourceReader
//            .readTextFileFromResource(context, R.raw.simple_vertex_shader);
//        String fragmentShaderSource = TextResourceReader
//            .readTextFileFromResource(context, R.raw.simple_fragment_shader);
//
//        int vertexShader = ShaderHelper.compileVertexShader(vertexShaderSource);
//        int fragmentShader = ShaderHelper.compileFragmentShader(fragmentShaderSource);
//
//        program = ShaderHelper.linkProgram(vertexShader, fragmentShader);
//
//        if (LoggerConfig.ON) {
//            ShaderHelper.validateProgram(program);
//        }
//
//        //编译-》连接-》使用这个opengl 程序
//        glUseProgram(program);
//
//        //查询颜色位置
//        aColorLocation = glGetAttribLocation(program,A_COLOR);
//
//        //获取属性的位置
//        aPositionLocation = glGetAttribLocation(program, A_POSITION);
//
//        uMatrixLocation = glGetUniformLocation(program,U_MARTRIX);
//
//        //把位置数据复制到本地vertexData缓存区中，并从头开始读取
//        // Bind our data, specified by the variable vertexData, to the vertex
//        // attribute at location A_POSITION_LOCATION.
//        // Bind our data, specified by the variable vertexData, to the vertex
//        // attribute at location A_POSITION_LOCATION.
//        vertexData.position(0);//读取顶点数据到缓存区
//        glVertexAttribPointer(aPositionLocation, POSITION_COMPONENT_COUNT, GL_FLOAT,
//                false, STRIDE, vertexData);
//
//        glEnableVertexAttribArray(aPositionLocation);
//
//        // Bind our data, specified by the variable vertexData, to the vertex
//        // attribute at location A_COLOR_LOCATION.
//        vertexData.position(POSITION_COMPONENT_COUNT); //读取颜色数据到缓存区
//        glVertexAttribPointer(aColorLocation, COLOR_COMPONENT_COUNT, GL_FLOAT,
//                false, STRIDE, vertexData);
//
//        glEnableVertexAttribArray(aColorLocation);

        //调用了glVertexAttribPointer opengl 就知道哪里读取a_Position数据了


        //方式二
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        table = new Table();
        mallet = new Mallet();

        textureProgram = new TextureShaderProgram(context);
        colorProgram = new ColorShaderProgram(context);

        texture = TextureHelper.loadTexture(context, R.drawable.air_hockey_surface);


        //第二个纹理
        textureBlend = TextureHelper.loadTexture(context,R.drawable.air_hockey_surface_low_res);
    }

    /**
     * onSurfaceChanged is called whenever the surface has changed. This is
     * called at least once when the surface is initialized. Keep in mind that
     * Android normally restarts an Activity on rotation, and in that case, the
     * renderer will be destroyed and a new one created.
     * 
     * @param width
     *            The new width, in pixels.
     * @param height
     *            The new height, in pixels.
     */
    @Override
    public void onSurfaceChanged(GL10 glUnused, int width, int height) {
        // Set the OpenGL viewport to fill the entire surface.
        //创建正交投影
//        final float aspectRatio = width > height?
//                (float) width/(float)height:(float) height/(float)width;
//        if (width > height) {
//            // Landscape
//            orthoM(projectionMatrix, 0, -aspectRatio, aspectRatio, -1f, 1f, -1f, 1f);
//        } else {
//            // Portrait or square
//            orthoM(projectionMatrix, 0, -1f, 1f, -aspectRatio, aspectRatio, -1f, 1f);
//        }

        //创建透视投影
//        MartixHelper.perspectiveM(projectionMatrix,45,(float)width / (float)height,1f,10f);
//
//        glViewport(0, 0, width, height);


        //方式二
        glViewport(0, 0, width, height);

        MartixHelper.perspectiveM(projectionMatrix, 45, (float) width
                / (float) height, 1f, 10f);

        setIdentityM(modelMatrix, 0);
        translateM(modelMatrix, 0, 0f, 0f, -2.5f);
        rotateM(modelMatrix, 0, -60f, 1f, 0f, 0f);

        final float[] temp = new float[16];
        multiplyMM(temp, 0, projectionMatrix, 0, modelMatrix, 0);
        System.arraycopy(temp, 0, projectionMatrix, 0, temp.length);
    }

    /**
     * OnDrawFrame is called whenever a new frame needs to be drawn. Normally,
     * this is done at the refresh rate of the screen.
     */
    @Override
    public void onDrawFrame(GL10 glUnused) {
        // Clear the rendering surface.
//        glClear(GL_COLOR_BUFFER_BIT);
//
//        glUniformMatrix4fv(uMatrixLocation,1,false,projectionMatrix,0);
//
//        // Draw the table.如果顶点与片段未加颜色可采用下面的方法
//        //glUniform4f(uColorLocation, 1.0f, 1.0f, 1.0f, 1.0f);
//
//        glDrawArrays(GL_TRIANGLE_FAN, 0, 6);
//
//        // Draw the center dividing line.
//        //glUniform4f(uColorLocation, 1.0f, 0.0f, 0.0f, 1.0f);
//
//        glDrawArrays(GL_LINES, 6, 2);
//
//        // Draw the first mallet blue.
//        //glUniform4f(uColorLocation, 0.0f, 0.0f, 1.0f, 1.0f);
//        glDrawArrays(GL_POINTS, 8, 1);
//
//        // Draw the second mallet red.
//        //glUniform4f(uColorLocation, 1.0f, 0.0f, 0.0f, 1.0f);
//        glDrawArrays(GL_POINTS, 9, 1);


        //方式二
        // Clear the rendering surface.
        glClear(GL_COLOR_BUFFER_BIT);

        // Draw the table.
        textureProgram.useProgram();
        textureProgram.setUniforms(projectionMatrix, texture);
        //使用混合纹理
        textureProgram.setBlenTextureUniforms(projectionMatrix,textureBlend);


        table.bindData(textureProgram);

        //绑定混合数据到opengl 纹理中
        table.bindBlened(textureProgram);


        table.draw();

        // Draw the mallets.
        colorProgram.useProgram();
        colorProgram.setUniforms(projectionMatrix);
        mallet.bindData(colorProgram);
        mallet.draw();




    }
}
