package d20Simulator;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL20.*;

public class d20Simulator extends AbstractOpenGLBase {

    private ShaderProgram shaderProgram;

    // Object 1:
    Entity icosahedron1 = new Icosahedron();

    // Object 2:
    Entity icosahedron2 = new Icosahedron();

    // Object 3:
    Entity icosahedron3 = new Icosahedron();

    // for fps
    private int sum = 0;
    private long time;

    public static void main(String[] args) {
        new d20Simulator().start("DSA Prober", 1000, 1000);
    }

    @Override
    protected void init() {
        shaderProgram = new ShaderProgram("project");
        glUseProgram(shaderProgram.getId());

        // Object 1:
        icosahedron1.initEntity(0);

        // Object 2:
        icosahedron2.initEntity(1);

        // Object 3:
        icosahedron3.initEntity(2);

        glEnable(GL_DEPTH_TEST); // activate z-Buffer
        glEnable(GL_CULL_FACE); // activate backface culling

        Matrix4 projectionMat = new Matrix4(1, 1000);
        int projectionsMatrix = glGetUniformLocation(shaderProgram.getId(), "pMat");
        glUniformMatrix4fv(projectionsMatrix, false, projectionMat.getValuesAsArray());
    }

    @Override
    public void update() {

        // Hold F to get the Framerate in console (may take a while to build up!)
        if (Input.keys[GLFW_KEY_F]) {
            calculateFPS();
        }

        // Object 1:
        icosahedron1.updateEntity();

        // Object 2:
        icosahedron2.updateEntity();

        // Object 3:
        icosahedron3.updateEntity();

        glClearColor(0.3f, 0.3f, 0.3f, 1);

    }

    // get fps (not optimal)
    private void calculateFPS() {
        long oldTime = time;
        time = System.nanoTime();
        time = time / 1000 / 1000 / 1000;

        if (time == oldTime) {
            sum++;
        } else {
            System.out.println(sum);
            sum = 0;
        }
    }

    @Override
    protected void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        int loc = glGetUniformLocation(shaderProgram.getId(), "mMat");

        // Object 1:
        icosahedron1.renderEntity(loc, icosahedron1.getVerticesCount(),-5, 0, -10);

        // Object 2:
        icosahedron2.renderEntity(loc, icosahedron2.getVerticesCount(),0, 0, -10);

        // Object 3:
        icosahedron3.renderEntity(loc, icosahedron3.getVerticesCount(),5, 0, -10);
    }

    @Override
    public void destroy() {
    }

}
