package com.example.lahm.dailytask.OpenGL;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Project Name:DailyTask
 * Package Name:com.example.lahm.dailytask.OpenGL
 * Created by lahm on 2017/4/18 下午8:11 .
 * <p>
 * https://github.com/lamster2018
 */

public class Cube {
    //立方体8个顶点
    float[] cubeVertices = new float[]{
            //上边4个
            0.5f, 0.5f, 0.5f,
            0.5f, -0.5f, 0.5f,
            -0.5f, -0.5f, 0.5f,
            -0.5f, 0.5f, 0.5f,
            //下边4个
            0.5f, 0.5f, -0.5f,
            0.5f, -0.5f, -0.5f,
            -0.5f, -0.5f, -0.5f,
            -0.5f, 0.5f, -0.5f
    };
    //6个面，一共12个三角形
    private byte[] cubeFacets = new byte[]{
            0, 1, 2,
            0, 2, 3,
            2, 3, 7,
            2, 6, 7,
            0, 3, 7,
            0, 4, 7,
            4, 5, 6,
            4, 6, 7,
            0, 1, 4,
            1, 4, 5,
            1, 2, 6,
            1, 5, 6
    };

    int[] taperColors = new int[]{
            65535, 0, 0, 0,
            0, 65535, 0, 0,
            0, 0, 65535, 0,
            65535, 65535, 0, 0
    };

    private FloatBuffer cubeVerticesBuffer;
    private ByteBuffer cubeFacetsBuffer;
    private IntBuffer taperColorsBuffer;
    private float rotate;

    public Cube() {
        cubeVerticesBuffer = BufferUtil.float2FloatBuffer(cubeVertices);
        cubeFacetsBuffer = ByteBuffer.wrap(cubeFacets);
        taperColorsBuffer = BufferUtil.int2IntBuffer(taperColors);
    }

    public void draw(GL10 gl) {
        //允许顶点坐标数组
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        //允许颜色值数组
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        //y轴旋转1f
        gl.glRotatef(0.5f, 0f, 1f, 0f);
        //x轴旋转1f
        gl.glRotatef(10f, 1f, 0f, 0f);
        //设置线条
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, cubeVerticesBuffer);
        //设置颜色
        gl.glColorPointer(4, GL10.GL_FIXED, 0, taperColorsBuffer);
        //绘制面
        gl.glDrawElements(GL10.GL_TRIANGLE_STRIP
                , cubeFacetsBuffer.remaining(),
                GL10.GL_UNSIGNED_BYTE, cubeFacetsBuffer);
        gl.glFinish();
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
        rotate += 1;
    }

}
