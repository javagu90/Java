package org.yourorghere;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

public class GLRenderer implements GLEventListener {

    public void init(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());
        gl.setSwapInterval(1);

        gl.glClearColor(0.0f, 166.0f, 201.0f, 0.1f);
        gl.glShadeModel(GL.GL_SMOOTH); // try setting this to GL_FLAT and see what happens.
        display(drawable);
    }  
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();

        if (height <= 0) { // avoid a divide by zero error!    
            height = 1;
        }
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }
   
    public void muñeco2(GLAutoDrawable drawable, int z){
      GL gl = drawable.getGL();
      //gl.glTranslatef(0.0f, 0.0f, -2.0f);
      // gl.glClear(GL.GL_COLOR_BUFFER_BIT);
         gl.glTranslatef(-.33f, 1.33f-(z*0.05f), -2.1f);
        
        gl.glBegin(GL.GL_POLYGON);///paracaidas-----------------------------
            gl.glColor3f(0.4235f, 0.6941f, 0.1333f);      
            gl.glVertex3f(0.0705f, -0.1411f, 0.0f);  
            gl.glVertex3f(0.1372f,- 0.1411f, 0.0f);  
            gl.glVertex3f(0.2274f,- 0.1137f, 0.0f);
            gl.glVertex3f(0.3215f,- 0.1333f, 0.0f);
            gl.glVertex3f(0.3725f,- 0.1803f, 0.0f);  
            gl.glVertex3f(0.3960f,- 0.2313f, 0.0f);  
            gl.glVertex3f(0.3960f,- 0.2980f, 0.0f);  
            gl.glVertex3f(0.3725f,- 0.2745f, 0.0f);  
            gl.glVertex3f(0.3372f,- 0.2627f, 0.0f);  
            gl.glVertex3f(0.3372f,- 0.2627f, 0.0f);  
            gl.glVertex3f(0.3058f,- 0.2666f, 0.0f);  
            gl.glVertex3f(0.2784f,- 0.2823f, 0.0f);  
            gl.glVertex3f(0.2470f,- 0.2588f, 0.0f); 
            gl.glVertex3f(0.2117f,- 0.2549f, 0.0f);  
            gl.glVertex3f(0.1686f,- 0.2823f, 0.0f);  
            gl.glVertex3f(0.1372f,- 0.2627f, 0.0f);
            gl.glVertex3f(0.0980f,- 0.2588f, 0.0f);  
            gl.glVertex3f(0.0666f,- 0.2862f, 0.0f);  
            gl.glVertex3f(0.0705f, -0.1411f, 0.0f);   
            gl.glEnd();
            
            gl.glBegin(GL.GL_POLYGON);///paracaidas 2-----------------------------
            gl.glColor3f(0.9529f, 0.8980f, 0.0705f);      
            gl.glVertex3f(0.1725f, -0.2862f, 0.0f);  
            gl.glVertex3f(0.1803f,- 0.2039f, 0.0f);  
            gl.glVertex3f(0.2f,- 0.1411f, 0.0f);
            gl.glVertex3f(0.2235f,- 0.1137f, 0.0f);
            gl.glVertex3f(0.2470f,- 0.1137f, 0.0f);  
            gl.glVertex3f(0.2784f,- 0.1686f, 0.0f);  
            gl.glVertex3f(0.2862f,- 0.2196f, 0.0f);  
            gl.glVertex3f(0.2823f,- 0.2784f, 0.0f);  
            gl.glVertex3f(0.2470f,- 0.2549f, 0.0f);  
            gl.glVertex3f(0.2078f,- 0.2549f, 0.0f);  
             gl.glVertex3f(0.1725f, -0.2862f, 0.0f);  
            gl.glEnd();
        
            gl.glBegin(GL.GL_POLYGON);///lineas-----------------------------
            gl.glColor3f(0.f, 0.0f, 0.0f);
            gl.glVertex3f(0.0627f, -0.2862f, 0.0f);  
            gl.glVertex3f(0.1176f,- 0.4353f, 0.0f);  
            gl.glVertex3f(0.1333f,- 0.4235f, 0.0f);
            gl.glVertex3f(0.0705f,- 0.2862f, 0.0f);
            gl.glVertex3f(0.0627f, -0.2862f, 0.0f);
            gl.glEnd();
            
            gl.glBegin(GL.GL_POLYGON);///linea 2-----------------------------
            gl.glColor3f(0.f, 0.0f, 0.0f);
            gl.glVertex3f(0.1686f, -0.2862f, 0.0f);  
            gl.glVertex3f(0.1843f,- 0.4431f, 0.0f);  
            gl.glVertex3f(0.1960f,- 0.4352f, 0.0f);
            gl.glVertex3f(0.1803f,- 0.2862f, 0.0f);
            gl.glVertex3f(0.1686f, -0.2862f, 0.0f);
            gl.glEnd();
            
            gl.glBegin(GL.GL_POLYGON);///linea 3-----------------------------
            gl.glColor3f(0.f, 0.0f, 0.0f);
            gl.glVertex3f(0.2745f, -0.2823f, 0.0f);  
            gl.glVertex3f(0.2352f,- 0.4274f, 0.0f);  
            gl.glVertex3f(0.2470f,- 0.4392f, 0.0f);
            gl.glVertex3f(0.2862f,- 0.2823f, 0.0f);
            gl.glVertex3f(0.2745f, -0.2823f, 0.0f);
            gl.glEnd();
            
            gl.glBegin(GL.GL_POLYGON);///linea 4-----------------------------
            gl.glColor3f(0.f, 0.0f, 0.0f);
            gl.glVertex3f(0.3882f, -0.2941f, 0.0f);  
            gl.glVertex3f(0.2941f,- 0.4352f, 0.0f);  
            gl.glVertex3f(0.309f,- 0.4470f, 0.0f);
            gl.glVertex3f(0.3960f,- 0.2980f, 0.0f);
            gl.glVertex3f(0.3882f, -0.2941f, 0.0f);
            gl.glEnd();
        
            
            gl.glBegin(GL.GL_POLYGON);///casco-----------------------------
            gl.glColor3f(0.02352f, 0.1529f, 0.4156f); 
            gl.glVertex3f(0.2156f, -0.4117f, 0.0f);  
            gl.glVertex3f(0.2274f,- 0.3960f, 0.0f);  
            gl.glVertex3f(0.2509f,- 0.4f, 0.0f);
            gl.glVertex3f(0.2431f,- 0.4196f, 0.0f);
            gl.glVertex3f(0.2352f, -0.4078f, 0.0f);
            gl.glVertex3f(0.2156f, -0.4117f, 0.0f);
            gl.glEnd();
            
            gl.glBegin(GL.GL_POLYGON);///cabeza-----------------------------
            gl.glColor3f(1.0f, 0.7725f, 0.6274f);
            gl.glVertex3f(0.2156f, -0.4156f, 0.0f);  
            gl.glVertex3f(0.2196f,- 0.4313f, 0.0f);  
            gl.glVertex3f(0.2392f,- 0.4274f, 0.0f);
            gl.glVertex3f(0.2470f,- 0.4156f, 0.0f);
            gl.glVertex3f(0.2313f, -0.4117f, 0.0f);
            gl.glVertex3f(0.2156f, -0.4156f, 0.0f);
            gl.glEnd();
        
            gl.glBegin(GL.GL_POLYGON);///traje-----------------------------
            gl.glColor3f(0.9764f, 0.7803f, 0.1490f); 
            gl.glVertex3f(0.2196f, -0.4313f, 0.0f);
            gl.glVertex3f(0.1921f, -0.4313f, 0.0f);
            gl.glVertex3f(0.1882f, -0.5098f, 0.0f);
            gl.glVertex3f(0.2196f, -0.5176f, 0.0f);
            gl.glVertex3f(0.2352f, -0.4980f, 0.0f);
            gl.glVertex3f(0.2549f, -0.4549f, 0.0f);
            gl.glVertex3f(0.2352f, -0.4274f, 0.0f);
            gl.glVertex3f(0.2196f, -0.4313f, 0.0f);
            gl.glEnd();
            
       gl.glBegin(GL.GL_POLYGON);///brazoderecho-----------------------------
            gl.glColor3f(0.9764f, 0.7803f, 0.1490f); 
            gl.glVertex3f(0.1607f, -0.4666f, 0.0f);
            gl.glVertex3f(0.1411f, -0.4274f, 0.0f);
            gl.glVertex3f(0.1176f, -0.4313f, 0.0f);
            gl.glVertex3f(0.1607f, -0.4901f, 0.0f);
            gl.glVertex3f(0.2f, -0.4549f, 0.0f);
            gl.glVertex3f(0.2f, -0.4313f, 0.0f);
            gl.glVertex3f(0.1607f, -0.4666f, 0.0f);
            gl.glEnd();
            
        gl.glBegin(GL.GL_POLYGON);///brazoderecho-----------------------------
            gl.glColor3f(0.9764f, 0.7803f, 0.1490f); 
            gl.glVertex3f(0.2784f, -0.5019f, 0.0f);
            gl.glVertex3f(0.3019f, -0.4549f, 0.0f);
            gl.glVertex3f(0.2901f, -0.4392f, 0.0f);
            gl.glVertex3f(0.2745f, -0.4705f, 0.0f);
            gl.glVertex3f(0.2431f, -0.4392f, 0.0f);
            gl.glVertex3f(0.2431f, -0.4705f, 0.0f);
            gl.glVertex3f(0.2784f, -0.5019f, 0.0f);
            gl.glEnd();
            
             gl.glBegin(GL.GL_POLYGON);///pierna derecha-----------------------------
            gl.glColor3f(0.9764f, 0.7803f, 0.1490f); 
            gl.glVertex3f(0.1843f, -0.5098f, 0.0f);
            gl.glVertex3f(0.1921f, -0.5529f, 0.0f);
            gl.glVertex3f(0.1686f, -0.5921f, 0.0f);
            gl.glVertex3f(0.1921f, -0.6f, 0.0f);
            gl.glVertex3f(0.2196f, -0.5568f, 0.0f);
            gl.glVertex3f(0.2195f, -0.5215f, 0.0f);
            gl.glVertex3f(0.2352f, -0.5529f, 0.0f);
            gl.glVertex3f(0.2156f, -0.5529f, 0.0f);
            gl.glEnd();
            
             gl.glBegin(GL.GL_POLYGON);///pierna izquierda-----------------------------
            gl.glColor3f(0.9764f, 0.7803f, 0.1490f); 
            gl.glVertex3f(0.2156f, -0.5137f, 0.0f);
            gl.glVertex3f(0.2392f, -0.5490f, 0.0f);
            gl.glVertex3f(0.2235f, -0.5960f, 0.0f);
            gl.glVertex3f(0.2392f, -0.5960f, 0.0f);
            gl.glVertex3f(0.2588f, -0.5529f, 0.0f);
            gl.glVertex3f(0.2313f, -0.4901f, 0.0f);
            gl.glVertex3f(0.2039f, -0.4901f, 0.0f);
            gl.glVertex3f(0.2156f, -0.5137f, 0.0f);
            gl.glEnd();
            
            gl.glBegin(GL.GL_POLYGON);///pie derecho-----------------------------
            gl.glColor3f(0.f, 0.0f, 0.0f);
            gl.glVertex3f(0.1725f, -0.5960f, 0.0f);
            gl.glVertex3f(0.1725f, -0.6196f, 0.0f);
            gl.glVertex3f(0.1960f, -0.6235f, 0.0f);
            gl.glVertex3f(0.1882f, -0.6f, 0.0f);
            gl.glVertex3f(0.1725f, -0.5960f, 0.0f);
            gl.glEnd();
            
            gl.glBegin(GL.GL_POLYGON);///pie derecho-----------------------------
            gl.glColor3f(0.f, 0.0f, 0.0f);
            gl.glVertex3f(0.2196f, -0.6f, 0.0f);
            gl.glVertex3f(0.2235f, -0.6235f, 0.0f);
            gl.glVertex3f(0.2431f, -0.6235f, 0.0f);
            gl.glVertex3f(0.2313f, -0.6f, 0.0f);
            gl.glVertex3f(0.2196f, -0.6f, 0.0f);
            //gl.glVertex3f(0.1725f, -0.5960f, 0.0f);
            gl.glEnd();
        gl.glFlush();
      
         gl.glTranslatef(.33f, -1.33f+(z*0.05f), 2.1f);
      gl.glFlush(); 
    }    
    public void muñeco1(GLAutoDrawable drawable, int y){
          GL gl = drawable.getGL();
     // gl.glTranslatef(0.0f, 0.0f, -2.0f);
     // gl.glClear(GL.GL_COLOR_BUFFER_BIT);
       gl.glTranslatef(-.3f, 1.0f-(y*0.05f), -2.0f);
        
        
        gl.glBegin(GL.GL_POLYGON);/////casco--------------------
            gl.glColor3f(0.02352f, 0.1529f, 0.4156f);   
            gl.glVertex3f(0.0509f,- 0.1490f, 0.0f);  
            gl.glVertex3f(0.0509f, -0.1333f, 0.0f);   
            gl.glVertex3f(0.0823f,- 0.1058f, 0.0f);  
            gl.glVertex3f(0.1137f,- 0.1058f, 0.0f); 
            gl.glVertex3f(0.1372f,- 0.1294f, 0.0f);  
            gl.glVertex3f(0.1372f,- 0.1686f, 0.0f);  
            gl.glVertex3f(0.1098f,- 0.1921f, 0.0f);  
            gl.glVertex3f(0.10982f,- 0.1686f, 0.0f);
            gl.glVertex3f(0.0980f,- 0.1411f, 0.0f);  
            gl.glVertex3f(0.0509f,- 0.1568f, 0.0f);  
        gl.glEnd();
        
        gl.glBegin(GL.GL_POLYGON);///cara-----------------------------
            gl.glColor3f(1.0f, 0.7725f, 0.6274f);   
            gl.glVertex3f(0.0549f,- 0.1529f, 0.0f);  
            gl.glVertex3f(0.0627f, -0.1882f, 0.0f);   
            gl.glVertex3f(0.0901f,- 0.2039f, 0.0f);  
            gl.glVertex3f(0.1098f,- 0.1921f, 0.0f); 
            gl.glVertex3f(0.0980f,- 0.1450f, 0.0f);  
            gl.glVertex3f(0.0666f,- 0.1450f, 0.0f);  
            gl.glVertex3f(0.0549f,- 0.1529f, 0.0f);  
        gl.glEnd();
        
        gl.glBegin(GL.GL_POLYGON);///boca-----------------------------
            gl.glColor3f(0.1921f, 0.1921f, 0.1921f);   
            gl.glVertex3f(0.0941f,- 0.1843f, 0.0f);  
            gl.glVertex3f(0.0901f, -0.1960f, 0.0f);   
            gl.glVertex3f(0.0901f,- 0.2039f, 0.0f);  
            gl.glVertex3f(0.0823f,- 0.2f, 0.0f); 
            gl.glVertex3f(0.0745f,- 0.1921f, 0.0f);  
            gl.glVertex3f(0.0862f,- 0.1921f, 0.0f);  
            gl.glVertex3f(0.0941f,- 0.1843f, 0.0f);  
        gl.glEnd();
        
        gl.glBegin(GL.GL_POLYGON);///gafas-----------------------------
            gl.glColor3f(0.8509f, 0.8509f, 0.8196f);   
            gl.glVertex3f(0.0549f,- 0.1647f, 0.0f);  
            gl.glVertex3f(0.0509f, -0.1764f, 0.0f);   
            gl.glVertex3f(0.0588f,- 0.1764f, 0.0f);  
            gl.glVertex3f(0.0705f,- 0.1764f, 0.0f); 
            gl.glVertex3f(0.1019f,- 0.1725f, 0.0f);  
            gl.glVertex3f(0.1019f,- 0.1490f, 0.0f);  
            gl.glVertex3f(0.0627f,- 0.1529f, 0.0f);
            gl.glVertex3f(0.0549f,- 0.1647f, 0.0f);
        gl.glEnd();
        
        gl.glBegin(GL.GL_POLYGON);///ojo-----------------------------
            gl.glColor3f(0.1921f, 0.1921f, 0.1921f);   
            gl.glVertex3f(0.0823f,- 0.1607f, 0.0f);  
            gl.glVertex3f(0.0862f, -0.1647f, 0.0f);   
            gl.glVertex3f(0.0862f,- 0.1686f, 0.0f);  
            gl.glVertex3f(0.0823f,- 0.1607f, 0.0f);  
        gl.glEnd();
        
        
        gl.glBegin(GL.GL_POLYGON);///cuerpo-----------------------------
            gl.glColor3f(0.9764f, 0.7803f, 0.1490f);   
            gl.glVertex3f(0.2117f,- 0.2039f, 0.0f);  
            gl.glVertex3f(0.2666f,- 0.2274f, 0.0f);
            gl.glVertex3f(0.2901f,- 0.2156f, 0.0f);
            gl.glVertex3f(0.2901f,- 0.1607f, 0.0f);
            gl.glVertex3f(0.2784f,- 0.1607f, 0.0f);
            gl.glVertex3f(0.2705f,- 0.2039f, 0.0f);
            gl.glVertex3f(0.2352f,- 0.1843f, 0.0f);
            gl.glVertex3f(0.2470f,- 0.1764f, 0.0f);
            gl.glVertex3f(0.2352f,- 0.1333f, 0.0f);
            gl.glVertex3f(0.2196f,- 0.1333f, 0.0f);
            gl.glVertex3f(0.2274f,- 0.1686f, 0.0f);
            gl.glVertex3f(0.1333f,- 0.1764f, 0.0f);
            gl.glVertex3f(0.07841f,- 0.2f, 0.0f);  
            gl.glVertex3f(0.0392f, -0.2392f, 0.0f);
            gl.glVertex3f(0.0431f,- 0.2470f, 0.0f);  
            gl.glVertex3f(0.0705f,- 0.2235f, 0.0f);
            gl.glVertex3f(0.1058f,- 0.2117f, 0.0f); 
            gl.glVertex3f(0.1725f,- 0.2117f, 0.0f); 
            gl.glVertex3f(0.2117f,- 0.2039f, 0.0f);  
        gl.glEnd();
        
        gl.glBegin(GL.GL_POLYGON);///brazo negro -----------------------------
            gl.glColor3f(0.1921f, 0.1921f, 0.1921f);   
            gl.glVertex3f(0.1411f,- 0.1921f, 0.0f);  
            gl.glVertex3f(0.1803f, -0.2196f, 0.0f);   
            gl.glVertex3f(0.2f,- 0.2431f, 0.0f);  
            gl.glVertex3f(0.2117f,- 0.2392f, 0.0f);
            gl.glVertex3f(0.1725f,- 0.1803f, 0.0f);
            gl.glVertex3f(0.1411f,- 0.1921f, 0.0f);  
        gl.glEnd();
        
        gl.glBegin(GL.GL_POLYGON);///brazo amarillo ----------------------------- 
            gl.glColor3f(0.9764f, 0.7803f, 0.1490f); 
            gl.glVertex3f(0.1490f,- 0.1921f, 0.0f);  
            gl.glVertex3f(0.1764f, -0.2078f, 0.0f);   
            gl.glVertex3f(0.2039f,- 0.2392f, 0.0f);  
            gl.glVertex3f(0.1921f,- 0.2117f, 0.0f);
            gl.glVertex3f(0.1647f,- 0.1843f, 0.0f);
            gl.glVertex3f(0.1490f,- 0.1921f, 0.0f);  
        gl.glEnd();
        
        gl.glBegin(GL.GL_POLYGON);///guantes -----------------------------
            gl.glColor3f(0.1921f, 0.1921f, 0.1921f);   
            gl.glVertex3f(0.0313f,- 0.2470f, 0.0f);  
            gl.glVertex3f(0.0156f, -0.2588f, 0.0f);   
            gl.glVertex3f(0.0274f,- 0.2705f, 0.0f);  
            gl.glVertex3f(0.0352f,- 0.2588f, 0.0f);
            gl.glVertex3f(0.0470f,- 0.2588f, 0.0f);
            gl.glVertex3f(0.0470f,- 0.2588f, 0.0f);  
            gl.glVertex3f(0.0470f,- 0.2470f, 0.0f);  
            gl.glVertex3f(0.0313f,- 0.2470f, 0.0f);  
            gl.glEnd();
            
            gl.glBegin(GL.GL_POLYGON);///guantes 2 -----------------------------
            gl.glColor3f(0.1921f, 0.1921f, 0.1921f);   
            gl.glVertex3f(0.2f,- 0.2431f, 0.0f);  
            gl.glVertex3f(0.2f, -0.2588f, 0.0f);   
            gl.glVertex3f(0.1882f,- 0.2705f, 0.0f);  
            gl.glVertex3f(0.1921f,- 0.2745f, 0.0f);
            gl.glVertex3f(0.2078f,- 0.2666f, 0.0f);
            gl.glVertex3f(0.2117f,- 0.2901f, 0.0f);  
            gl.glVertex3f(0.2274f,- 0.2705f, 0.0f);  
            gl.glVertex3f(0.2078f,- 0.2392f, 0.0f);
            gl.glVertex3f(0.2f,- 0.2431f, 0.0f);  
            gl.glEnd();
            
            gl.glBegin(GL.GL_POLYGON);///pie derecho -----------------------------
            gl.glColor3f(0.1921f, 0.1921f, 0.1921f);   
            gl.glVertex3f(0.2784f,- 0.1607f, 0.0f);  
            gl.glVertex3f(0.2823f, -0.1450f, 0.0f);   
            gl.glVertex3f(0.3176f,- 0.1411f, 0.0f);  
            gl.glVertex3f(0.3372f,- 0.1529f, 0.0f);
            gl.glVertex3f(0.3254f,- 0.1647f, 0.0f);
            gl.glVertex3f(0.3058f,- 0.1568f, 0.0f);  
            gl.glVertex3f(0.2941f,- 0.1568f, 0.0f);  
            gl.glVertex3f(0.2901f,- 0.1607f, 0.0f);
            gl.glVertex3f(0.2784f,- 0.1607f, 0.0f);  
            gl.glEnd();
        
            gl.glBegin(GL.GL_POLYGON);///pie izquierdo -----------------------------
            gl.glColor3f(0.1921f, 0.1921f, 0.1921f);   
            gl.glVertex3f(0.2235f,- 0.1372f, 0.0f);  
            gl.glVertex3f(0.2078f, -0.1254f, 0.0f);   
            gl.glVertex3f(0.2431f,- 0.0941f, 0.0f);  
            gl.glVertex3f(0.2470f,- 0.1019f, 0.0f);
            gl.glVertex3f(0.2274f,- 0.1215f, 0.0f);
            gl.glVertex3f(0.2313f,- 0.1333f, 0.0f);  
            gl.glVertex3f(0.2235f,- 0.1372f, 0.0f);  
            gl.glEnd();

            gl.glBegin(GL.GL_POLYGON);///mochila -----------------------------
            gl.glColor3f(0.8862f, 0.4431f, 0.1843f);   
            gl.glVertex3f(0.1372f,- 0.1764f, 0.0f);  
            gl.glVertex3f(0.1411f, -0.1411f, 0.0f);   
            gl.glVertex3f(0.1843f,- 0.1372f, 0.0f);  
            gl.glVertex3f(0.2f,- 0.1490f, 0.0f);
            gl.glVertex3f(0.2039f,- 0.1725f, 0.0f);
            gl.glVertex3f(0.1372f,- 0.1764f, 0.0f);  
            gl.glEnd();
            
                   //gl.glTranslatef(0.3f, -1.0f, 2.0f);
            
       gl.glTranslatef(.3f, -1.0f+(y*0.05f), 2.0f);
       gl.glFlush(); 
    }
    public void avion(GLAutoDrawable drawable, int x){
      GL gl = drawable.getGL();
         // gl.glClear(GL.GL_COLOR_BUFFER_BIT);
          gl.glTranslatef(-1.33f+(x*.05f), 1.33f, -2.1f);
      
        gl.glBegin(GL.GL_POLYGON);
            gl.glColor3f(1.0f, 1.0f, 1.0f);   
            gl.glVertex3f(0.0117f,- 0.0549f, 0.0f);  
            gl.glVertex3f(0.0509f, -0.0509f, 0.0f);   
            gl.glVertex3f(0.0862f,- 0.0862f, 0.0f);  
            gl.glVertex3f(0.0666f,- 0.1137f, 0.0f); 
            gl.glVertex3f(0.0117f,- 0.0549f, 0.0f);  
        gl.glEnd();
       
        gl.glBegin(GL.GL_POLYGON);
            gl.glColor3f(1.0f, 1.0f, 1.0f); 
            gl.glVertex3f(0.0862f,- 0.0862f, 0.0f);  
            gl.glVertex3f(0.0705f ,- 0.1137f, 0.0f);   
            gl.glVertex3f(0.2352f ,- 0.0862f, 0.0f);  
            gl.glVertex3f(0.1843f ,- 0.1333f, 0.0f); 
             gl.glVertex3f(0.0862f,- 0.0862f, 0.0f); 
        gl.glEnd();

        gl.glBegin(GL.GL_POLYGON);
            gl.glColor3f(1.0f, 1.0f, 1.0f);   
            gl.glVertex3f(0.2117f,- 0.0862f, 0.0f);  
            gl.glVertex3f(0.2078f ,- 0.1333f, 0.0f);   
            gl.glVertex3f(0.3333f ,- 0.1333f, 0.0f);  
            gl.glVertex3f(0.3294f ,- 0.0784f, 0.0f); 
             gl.glVertex3f(0.2549f,- 0.0823f, 0.0f);  
        gl.glEnd();
        ///cabeza
         gl.glBegin(GL.GL_POLYGON);
            gl.glColor3f(1.0f, 1.0f, 1.0f);   
            gl.glVertex3f(0.3294f,- 0.0784f, 0.0f);  
            gl.glVertex3f(0.3529f ,- 0.0941f, 0.0f);   
            gl.glVertex3f(0.3725f ,- 0.0980f, 0.0f);  
            gl.glVertex3f(0.3725f ,- 0.1137f, 0.0f); 
            gl.glVertex3f(0.3529f,- 0.1254f, 0.0f);  
            gl.glVertex3f(0.3333f,- 0.1294f, 0.0f); 
            gl.glVertex3f(0.3294f,- 0.0784f, 0.0f); 
        gl.glEnd();
        ///ala izq
        gl.glBegin(GL.GL_POLYGON);
            gl.glColor3f(1.0f, 1.0f, 1.0f);   
            gl.glVertex3f(0.2470f,- 0.0784f, 0.0f);  
            gl.glVertex3f(0.3019f ,- 0.0292f, 0.0f);   
            gl.glVertex3f(0.3215f ,- 0.0292f, 0.0f);  
            gl.glVertex3f(0.2745f,- 0.0862f, 0.0f);  
            gl.glVertex3f(0.2470f,- 0.0784f, 0.0f);  
        gl.glEnd();
        
        ///cola2
        gl.glBegin(GL.GL_POLYGON);
            gl.glColor3f(1.0f, 1.0f, 1.0f);   
            gl.glVertex3f(0.0588f,- 0.0980f, 0.0f);  
            gl.glVertex3f(0.0235f ,- 0.1176f, 0.0f);   
            gl.glVertex3f(0.0431f ,- 0.1215f, 0.0f);  
            gl.glVertex3f(0.0784f,- 0.1098f, 0.0f);  
            gl.glVertex3f(0.0588f,- 0.0980f, 0.0f);  
        gl.glEnd();
        
         ///ala derecha negro
          gl.glBegin(GL.GL_POLYGON);
            gl.glColor3f(0.0f, 0.0f, 0.0f);   
            gl.glVertex3f(0.1882f,- 0.1098f, 0.0f);  
            gl.glVertex3f(0.2588f ,- 0.1137f, 0.0f);   
            gl.glVertex3f(0.1411f ,- 0.1725f, 0.0f);  
            gl.glVertex3f(0.0745f,- 0.1647f, 0.0f);  
            gl.glVertex3f(0.1058f,- 0.1529f, 0.0f);
            gl.glVertex3f(0.1882f,- 0.1098f, 0.0f);  
          gl.glEnd();
          
        
        ///ala derecha
          gl.glBegin(GL.GL_POLYGON);
            gl.glColor3f(1.0f, 1.0f, 1.0f);   
            gl.glVertex3f(0.2039f,- 0.1098f, 0.0f);  
            gl.glVertex3f(0.25490f ,- 0.1137f, 0.0f);   
            gl.glVertex3f(0.1294f ,- 0.1725f, 0.0f);  
            gl.glVertex3f(0.0784f,- 0.1607f, 0.0f);  
            gl.glVertex3f(0.1058f,- 0.1568f, 0.0f);
            gl.glVertex3f(0.2039f,- 0.1098f, 0.0f);  
          gl.glEnd();
          
           ///ventana grande
          gl.glBegin(GL.GL_POLYGON);
            gl.glColor3f(0.0f, 0.0f, 0.0f);   
            gl.glVertex3f(0.3372f,- 0.0823f, 0.0f);  
            gl.glVertex3f(0.3254f ,- 0.0901f, 0.0f);   
            gl.glVertex3f(0.3333f ,- 0.1019f, 0.0f);  
            gl.glVertex3f(0.3529f,- 0.0941f, 0.0f);  
            gl.glVertex3f(0.3450f,- 0.09019f, 0.0f);
            gl.glVertex3f(0.3372f,- 0.0823f, 0.0f);  
          gl.glEnd();
          
          ///ventana chica
          gl.glBegin(GL.GL_POLYGON);
            gl.glColor3f(0.0f, 0.0f, 0.0f);   
            gl.glVertex3f(0.1176f,- 0.09803f, 0.0f);  
            gl.glVertex3f(0.1294f ,- 0.0980f, 0.0f);   
            gl.glVertex3f(0.1294f ,- 0.1098f, 0.0f);  
            gl.glVertex3f(0.1176f,- 0.11372f, 0.0f);  
            gl.glVertex3f(0.1176f,- 0.09803f, 0.0f);
          gl.glEnd();
            
        gl.glBegin(GL.GL_POLYGON);
            gl.glColor3f(0.0f, 0.0f, 0.0f);   
            gl.glVertex3f(0.1725f,- 0.09803f, 0.0f);  
            gl.glVertex3f(0.1882f ,- 0.09803f, 0.0f);   
            gl.glVertex3f(0.1882f ,- 0.1137f, 0.0f);  
            gl.glVertex3f(0.1725f,- 0.1137f, 0.0f);  
            gl.glVertex3f(0.1725f,- 0.09803f, 0.0f);
        gl.glEnd();
        
        gl.glBegin(GL.GL_POLYGON);
            gl.glColor3f(0.0f, 0.0f, 0.0f);   
            gl.glVertex3f(0.2078f,- 0.09803f, 0.0f);  
            gl.glVertex3f(0.2235f ,- 0.09803f, 0.0f);   
            gl.glVertex3f(0.2235f ,- 0.1137f, 0.0f);  
            gl.glVertex3f(0.2078f,- 0.1176f, 0.0f);  
            gl.glVertex3f(0.2078f,- 0.09803f, 0.0f);
        gl.glEnd();
        
        gl.glBegin(GL.GL_POLYGON);
            gl.glColor3f(0.0f, 0.0f, 0.0f);   
            gl.glVertex3f(0.2431f,- 0.09803f, 0.0f);  
            gl.glVertex3f(0.2588f ,- 0.09803f, 0.0f);   
            gl.glVertex3f(0.2588f ,- 0.1137f, 0.0f);  
            gl.glVertex3f(0.2431f,- 0.1137f, 0.0f);  
            gl.glVertex3f(0.2431f,- 0.09803f, 0.0f);
        gl.glEnd();
        
        gl.glBegin(GL.GL_POLYGON);
            gl.glColor3f(0.0f, 0.0f, 0.0f);   
            gl.glVertex3f(0.2784f,- 0.09803f, 0.0f);  
            gl.glVertex3f(0.2901f ,- 0.09803f, 0.0f);   
            gl.glVertex3f(0.2941f ,- 0.1098f, 0.0f);  
            gl.glVertex3f(0.2784f,- 0.1098f, 0.0f);  
            gl.glVertex3f(0.2784f,- 0.09803f, 0.0f);
        gl.glEnd();
      
        ////turbinas
        gl.glBegin(GL.GL_POLYGON);
            gl.glColor3f(0.141f, 0.4431f, 0.0f);   
            gl.glVertex3f(0.1803f,- 0.1529f, 0.0f);  
            gl.glVertex3f(0.2039f ,- 0.1607f, 0.0f);   
            gl.glVertex3f(0.2235f ,- 0.1490f, 0.0f);  
            gl.glVertex3f(0.2196f,- 0.13333f, 0.0f);  
            gl.glVertex3f(0.1803f,- 0.1529f, 0.0f);
        gl.glEnd();
        
        gl.glBegin(GL.GL_POLYGON);
            gl.glColor3f(0.141f, 0.4431f, 0.0f);   
            gl.glVertex3f(0.1372f,- 0.1647f, 0.0f);  
            gl.glVertex3f(0.1568f ,- 0.1725f, 0.0f);   
            gl.glVertex3f(0.1764f ,- 0.1686f, 0.0f);  
            gl.glVertex3f(0.1725f,- 0.1490f, 0.0f);  
            gl.glVertex3f(0.1372f,- 0.1647f, 0.0f);
        gl.glEnd();
        //gl.glFlush();
        gl.glTranslatef(+1.33f-(x*.05f), -1.33f, +2.1f);
        gl.glFlush(); 
      //}
    }
    public void fondo(GLAutoDrawable drawable){
      GL gl = drawable.getGL();
      
      //gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glColor3f(0.44f, 0.117f, 0.29f);
        gl.glBegin(GL.GL_TRIANGLES);
            gl.glVertex3f(-0.0f, -0.5f, 0.0f);  
            gl.glVertex3f(-0.42f, -0.25f, 0.0f); 
            gl.glVertex3f(-0.72f, -0.5f, 0.0f); 
        gl.glEnd();
        
        gl.glColor3f(0.44f, 0.117f, 0.29f);
        gl.glBegin(GL.GL_TRIANGLES);
            gl.glVertex3f(0.6f, -0.5f, 0.0f);  
            gl.glVertex3f(0.30f, -0.25f, 0.0f); 
            gl.glVertex3f(-0.2f, -0.5f, 0.0f); 
        gl.glEnd();

      
        gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(0.176f, 0.675f, 0.433f);    // Set the current drawing color to light blue
            gl.glVertex3f(-.72f, -0.5f, 0.0f);  // Top Left
            gl.glVertex3f(0.72f, -0.5f, 0.0f);   // Top Right
            gl.glVertex3f(0.72f, -0.83f, 0.0f);  // Bottom Right
            gl.glVertex3f(-.72f, -0.83f, 0.0f); // Bottom Left
        gl.glEnd();
        
        
        gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(0.176f, 0.675f, 0.433f);    // Set the current drawing color to light blue
            gl.glVertex3f(-.72f, -0.5f, 0.0f);  // Top Left
            gl.glVertex3f(0.72f, -0.5f, 0.0f);   // Top Right
            gl.glVertex3f(0.72f, -0.83f, 0.0f);  // Bottom Right
            gl.glVertex3f(-.72f, -0.83f, 0.0f); // Bottom Left
        gl.glEnd();
        gl.glFlush(); 
    }
     
    public void display(GLAutoDrawable drawable) {
     
        GL gl = drawable.getGL();   
                gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
                gl.glLoadIdentity();
                  
           for(int vvi=0;vvi<=35;vvi++){
                
                gl.glTranslatef(+0.0f, +0.0f, -2.0f);
                  gl.glClear(GL.GL_COLOR_BUFFER_BIT);
                  
                fondo(drawable); 
                avion(drawable,vvi);
                if(vvi<8){
                    muñeco1(drawable,vvi);
                }else{
                    muñeco2(drawable,vvi);
                }
                
                    gl.glTranslatef(-0.0f, -0.0f, +2.0f);        

                   //Thread.sleep(10);
                  gl.glFlush(); 

                //    vvi=vvi+1;
           }   
       }
        
    
    
    
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}

