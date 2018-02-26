//package com.example.cuibowen.animation.utils;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.opengl.GLSurfaceView;
//import android.opengl.GLUtils;
//
//import com.example.cuibowen.animation.R;
//
//import java.nio.ByteBuffer;
//import java.nio.FloatBuffer;
//
//import javax.microedition.khronos.egl.EGLConfig;
//import javax.microedition.khronos.opengles.GL10;
//
///**
// * Created by cuibowen on 2017/11/27.
// */
//
//public class MyRenderer implements GLSurfaceView.Renderer{
//
//    private float[] cubeVertices = {
//            -0.6f, -0.6f, 0.0f,
//            -0.6f, 0.6f,0.0f,
//            0.6f, 0.6f, 0.0f,
//            0.6f, 0.6f, 0.0f,
//            0.6f, -0.6f, 0.0f,
//            -0.6f, -0.6f, 0.0f,
//    };
//
//    // 定义立方体所需要的6个面（一共是12个三角形所需的顶点）
//    private byte[] cubeFacets = { 0, 1, 2, 3, 4, 5,
//    };
//    // 定义纹理贴图的座标数据
//    private float[] cubeTextures = {
//            1.0000f, 1.0000f,
//            1.0000f, 0.0000f,
//            0.0000f, 0.0000f,
//            0.0000f, 0.0000f,
//            0.0000f, 1.0000f,
//            1.0000f, 1.0000f,
//
////          0.0f,0.0f,
////          0.0f,1.0f,
////          1.0f,1.0f,
////          1.0f,1.0f,
////          1.0f,0.0f,
////          0.0f,0.0f,
//
//    };
//
//    private Context context;
//    private FloatBuffer cubeVerticesBuffer;
//    private ByteBuffer cubeFacetsBuffer;
//    private FloatBuffer cubeTexturesBuffer;
//    // 定义本程序所使用的纹理
//    private int texture;
//
//    MyRenderer(Context main){
//
//        this.context = main;
//        // 将立方体的顶点位置数据数组包装成FloatBuffer;
//        cubeVerticesBuffer = BufferUtil.floatToBuffer(cubeVertices);
//        // 将立方体的6个面（12个三角形）的数组包装成ByteBuffer
//        cubeFacetsBuffer = BufferUtil.byteToBuffer(cubeFacets);
//        // 将立方体的纹理贴图的座标数据包装成FloatBuffer
//        cubeTexturesBuffer = BufferUtil.floatToBuffer(cubeTextures);
//
//        // 将立方体的顶点位置数据数组包装成FloatBuffer;
////      cubeVerticesBuffer = FloatBuffer.wrap(cubeVertices);
////      // 将立方体的6个面（12个三角形）的数组包装成ByteBuffer
////      cubeFacetsBuffer = ByteBuffer.wrap(cubeFacets);
////      // 将立方体的纹理贴图的座标数据包装成FloatBuffer
////      cubeTexturesBuffer = FloatBuffer.wrap(cubeTextures);
//
//    }
//
//    @Override
//    public void onDrawFrame(GL10 gl) {
//        // 清除屏幕缓存和深度缓存
//        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
//        // 启用顶点座标数据
//        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
//        // 启用贴图座标数组数据
//        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
//        // 设置当前矩阵模式为模型视图。
//        gl.glMatrixMode(GL10.GL_MODELVIEW);
//        // --------------------绘制第一个图形---------------------
//        gl.glLoadIdentity();
//        // 把绘图中心移入屏幕2个单位
//        gl.glTranslatef(0f, 0.0f, -2.0f);
//        // 旋转图形
////      gl.glRotatef(angley, 0, 1, 0);
////      gl.glRotatef(anglex, 1, 0, 0);
//        // 设置顶点的位置数据
//        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, cubeVerticesBuffer);
//        // 设置贴图的的座标数据
//        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, cubeTexturesBuffer);
//        // 执行纹理贴图
//        gl.glBindTexture(GL10.GL_TEXTURE_2D, texture);
//        // 按cubeFacetsBuffer指定的面绘制三角形
//        gl.glDrawElements(GL10.GL_TRIANGLES, cubeFacetsBuffer.remaining(),
//                GL10.GL_UNSIGNED_BYTE, cubeFacetsBuffer);
//        // 绘制结束
//        gl.glFinish();
//        // 禁用顶点、纹理座标数组
//        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
//        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
//    }
//
//    @Override
//    public void onSurfaceChanged(GL10 gl, int width, int height) {
//        // 设置3D视窗的大小及位置
//        gl.glViewport(0, 0, width, height);
//        // 将当前矩阵模式设为投影矩阵
//        gl.glMatrixMode(GL10.GL_PROJECTION);
//        // 初始化单位矩阵
//        gl.glLoadIdentity();
//        // 计算透视视窗的宽度、高度比
//        float ratio = (float) width / height;
//        // 调用此方法设置透视视窗的空间大小。
//        gl.glFrustumf(-ratio, ratio, -1, 1, 1, 10);
//    }
//
//    @Override
//    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
//        // 关闭抗抖动
//        gl.glDisable(GL10.GL_DITHER);
//        // 设置系统对透视进行修正
//        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
//        gl.glClearColor(0, 0, 0, 0);
//        // 设置阴影平滑模式
//        gl.glShadeModel(GL10.GL_SMOOTH);
//        // 启用深度测试
//        gl.glEnable(GL10.GL_DEPTH_TEST);
//        // 设置深度测试的类型
//        gl.glDepthFunc(GL10.GL_LEQUAL);
//        // 启用2D纹理贴图
//        gl.glEnable(GL10.GL_TEXTURE_2D);
//        // 装载纹理
//        loadTexture(gl);
//    }
//
//    private void loadTexture(GL10 gl)
//    {
//        Bitmap bitmap = null;
//        try
//        {
//            // 加载位图
//            bitmap = BitmapFactory.decodeResource(context.getResources(),
//                    R.drawable.sand);
//            int[] textures = new int[1];
//            // 指定生成N个纹理（第一个参数指定生成1个纹理），
//            // textures数组将负责存储所有纹理的代号。
//            gl.glGenTextures(1, textures, 0);
//            // 获取textures纹理数组中的第一个纹理
//            texture = textures[0];
//            // 通知OpenGL将texture纹理绑定到GL10.GL_TEXTURE_2D目标中
//            gl.glBindTexture(GL10.GL_TEXTURE_2D, texture);
//            // 设置纹理被缩小（距离视点很远时被缩小）时候的滤波方式
//            gl.glTexParameterf(GL10.GL_TEXTURE_2D,
//                    GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
//            // 设置纹理被放大（距离视点很近时被方法）时候的滤波方式
//            gl.glTexParameterf(GL10.GL_TEXTURE_2D,
//                    GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
//            // 设置在横向、纵向上都是平铺纹理
//            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S,
//                    GL10.GL_REPEAT);
//            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T,
//                    GL10.GL_REPEAT);
//            // 加载位图生成纹理
//            GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);       }
//        finally
//        {
//            // 生成纹理之后，回收位图
//            if (bitmap != null)
//                bitmap.recycle();
//        }
//    }
//}
