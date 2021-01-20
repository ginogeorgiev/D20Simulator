package d20Simulator;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_V;

public class Tetrahedron extends Entity {

    private float posX = 0;
    private float posY = 0;
    private float posZ = 0;
    private float rotate = 0;


    public Tetrahedron() {

        srcTextureList = new String[]{"dice4black.png", "dice4red.png"};
        srcTextureListIterator = 0;
        frame = 0;
        lastUpdateColorChange = frame;
        verticesCount = 24;

        verticesCoordinates = new float[]{
                0.5f, 0.5f, -0.5f, // A
                -0.5f, -0.5f, -0.5f, // C
                -0.5f, 0.5f, 0.5f, // F

                -0.5f, 0.5f, 0.5f, // F
                -0.5f, -0.5f, -0.5f, // C
                0.5f, -0.5f, 0.5f, // H

                0.5f, 0.5f, -0.5f, // A
                0.5f, -0.5f, 0.5f, // H
                -0.5f, -0.5f, -0.5f, // C

                -0.5f, 0.5f, 0.5f, // F
                0.5f, -0.5f, 0.5f, // H
                0.5f, 0.5f, -0.5f, // A
        };

        uvCoordinates = new float[]{
                // 1
                0.125f, 0,
                0.0f, 1,
                0.25f, 1,

                // 2
                0.375f, 0,
                0.25f, 1,
                0.5f, 1,

                // 3
                0.625f, 0,
                0.5f, 1,
                0.75f, 1,

                // 4
                0.875f, 0,
                0.75f, 1,
                1f, 1,
        };

        normalVectors = new float[]{
                -1f, 1f, -1f, // A
                -1f, 1f, -1f, // C
                -1f, 1f, -1f, // F

                -1f, -1f, 1f, // F
                -1f, -1f, 1f, // C
                -1f, -1f, 1f, // H

                1f, -1f, -1f, // A
                1f, -1f, -1f, // H
                1f, -1f, -1f, // C

                1f, 1f, 1f, // F
                1f, 1f, 1f, // H
                1f, 1f, 1f, // A
        };
    }

    @Override
    public void updateEntity() {
        modelMat = new Matrix4();

        modelMat.rotateX(rotate).rotateY(rotate).translate(posX, posY, posZ);

        // Use WASD to move the tetrahedron around
        // Use Q and E to go back and forth
        // Use R to let it rotate
        // Use V to bring Object to view

        if (Input.keys[GLFW_KEY_Q]) {
            posZ = posZ + 0.05f;
        }
        if (Input.keys[GLFW_KEY_E]) {
            posZ = posZ - 0.05f;
        }
        if (Input.keys[GLFW_KEY_W]) {
            posY = posY + 0.05f;
        }
        if (Input.keys[GLFW_KEY_S]) {
            posY = posY - 0.05f;
        }
        if (Input.keys[GLFW_KEY_D]) {
            posX = posX + 0.05f;
        }
        if (Input.keys[GLFW_KEY_A]) {
            posX = posX - 0.05f;
        }
        if (Input.keys[GLFW_KEY_R]) {
            rotate = rotate - 1.5f;
        }
        if (Input.keys[GLFW_KEY_V]) {
            posZ = -5;
            posX = 0;
            posY = 0;
            rotate = 0;
        }

        // Use Left arrow to change color of the dice
        if(Input.keys[GLFW_KEY_RIGHT] && frame > lastUpdateColorChange + 30){
            changeTexture(srcTextureListIterator);
            lastUpdateColorChange = frame;
        }
        frame++;
    }

    @Override
    protected void renderEntity(int loc, int verticesCount, float startPosX, float startPosY, float startPosZ) {
        super.renderEntity(loc, verticesCount, startPosX, startPosY, startPosZ);
    }

    @Override
    protected void calculateNormalVectors(float[] verticesCoordinates) {
        super.calculateNormalVectors(verticesCoordinates);
    }


}
