
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.GraphicsConfiguration;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

public class EscenaGrafica {
    private Canvas3D canvas3D;
    public TransformGroup tg;

    public EscenaGrafica(){
        
        // 0) CONFIGURACION GRAFICA DEL MONITOR 
        /*Se interroga al hardware de la maquina con getPreferredConfiguration() 
        y elresultado de la consulta se le asigna a un  objeto 
        GraphicsConfiguration(En este caso  el objeto es gc).
        
        Uno de los métodos que fabrican objetos del tipo GraphicsConfiguration 
        es getPreferredConfiguration() de la clase SimpleUniverse*/
        
        // 1) creacion de un Objeto Canvas3D
        // 2) Creacion de un Objeto del tipo SimpleUniverse
        // 4) Creacion de Un BranchGroup (Puntos de insercion)   
        // Este objeto BranchGroup, pasa a ser la base de la rama Volumetrica.
        // 5) Creacion de un Objeto del tipo TransformGroup
        // 6) Creacion de un Obj. ColorCube
        //ColorCube es una clase que desciende, de Shape3D,
        // como argumento se le pasa la longitud de la arista
        // 7) Se agrega tg al obj.BranchGroup
        /* Para agregar elementos al objeto tipo BranchGroup se utiliza el método
        addChild de esta clase.*/
        // 8) Al obj. tg se le agrega el Obj. color
        // 9) Compilar la rama de volumen
        // 10) Agregar el BranchGroup al SimpleUniverse
        
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration(); // 0
        canvas3D = new Canvas3D(config); // 1)  
        SimpleUniverse su = new SimpleUniverse(canvas3D); // 2)
        
        // CAMBIO 1
        //******************************************************************
        double x,y,z;
        double r =10.0,phi= 45.0,theta = 45.0;
        // x = r*cos(phi)*sin(theta)
        // z = r*cos(phi)*cos(theta)
        // y = r*sin(theta)
        x = r*Math.cos(Math.toRadians(phi))*Math.sin(Math.toRadians(theta));
        z = r*Math.cos(Math.toRadians(phi))*Math.cos(Math.toRadians(theta));
        y = r*Math.sin(Math.toRadians(phi));
        Transform3D t3d = new Transform3D();
        t3d.lookAt(new Point3d(x,y,z), new Point3d(0.0,0.0,0.0), new Vector3d(0.0,0.0,1.0));
        t3d.invert();
        su.getViewingPlatform().getViewPlatformTransform().setTransform(t3d);
        //******************************************************************
        
        BranchGroup bg = new BranchGroup();// 4) 
        tg = new TransformGroup(); // 5) 
        ColorCube cubo = new ColorCube(0.8); // 6) 
        bg.addChild(tg); // 7) 
        tg.addChild(cubo); // 8)     
        bg.compile(); // 9) 
        su.addBranchGraph(bg); // 10)   
    }

    public Canvas3D getLienzo(){
        return canvas3D;
    }

}
