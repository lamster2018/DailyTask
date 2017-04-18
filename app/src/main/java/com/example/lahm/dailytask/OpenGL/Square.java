package com.example.lahm.dailytask.OpenGL;


import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Project Name:DailyTask
 * Package Name:com.example.lahm.dailytask.OpenGL
 * Created by lahm on 2017/4/18 下午2:45 .
 * <p>
 * https://github.com/lamster2018
 * http://wiki.jikexueyuan.com/project/opengl-es-basics/3d-drawing-basic-concepts.html
 */

public class Square {
    // Our vertices.
    private float vertices[] = {
            -1.0f, 1.0f, 0.0f,  // 0, Top Left
            -1.0f, -1.0f, 0.0f,  // 1, Bottom Left
            1.0f, -1.0f, 0.0f,  // 2, Bottom Right
            1.0f, 1.0f, 0.0f,  // 3, Top Right
    };

    // The order we like to connect them.
    private short[] indices = {0, 1, 2, 0, 2, 3};

    // Our vertex buffer.
    private FloatBuffer vertexBuffer;

    // Our index buffer.
    private ShortBuffer indexBuffer;

    public Square() {
        vertexBuffer = BufferUtil.float2FloatBuffer(vertices);
        indexBuffer = BufferUtil.short2ShortBuffer(indices);
    }

    /**
     * This function draws our square on screen.
     *
     * @param gl
     */
    public void draw(GL10 gl) {
        // 逆时针绘制
        gl.glFrontFace(GL10.GL_CCW);
        // Enable face culling.
        gl.glEnable(GL10.GL_CULL_FACE);
        // What faces to remove with the face culling.
        gl.glCullFace(GL10.GL_BACK);

        // 启用顶点坐标数组
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        // 设置顶点位置数据，第一个参数指定多少个元素设置一个坐标，一般是3，
        // 第二个参数，第一个参数如果是float，这里就设置GL10.GL_FLOAT来解析，
        // 第四个参数，是形如（x1,y1,z1,...,xN,yN,zN）的一维坐标数组，前边经过处理为buffer了
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);

        gl.glDrawElements(GL10.GL_TRIANGLES, indices.length,
                GL10.GL_UNSIGNED_SHORT, indexBuffer);

        // 结束绘制，关闭设置
        gl.glFinish();
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisable(GL10.GL_CULL_FACE);
    }
}

