package d20Simulator;

import java.util.Random;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;

public class Icosahedron extends Entity {

    private float duration;
    private float yPeak;
    private float angle;
    private int bounceFrame = 0;
    private Random random = new Random();
    private int lastUpdateBounce = frame;
    private boolean firstAnimation = true;
    private int loop;

    public Icosahedron() {

        srcTextureList = new String[]{"dice20red.png", "dice20black.png", "dice20green.png"};
        frame = 0;
        lastUpdateColorChange = frame;
        verticesCount = 60;

        verticesCoordinates = new float[]{
                -1, 1.618f, 0, // A
                0, 1, 1.618f, // L
                1, 1.618f, 0, // B

                -1, 1.618f, 0, // A
                1, 1.618f, 0, // B
                0, 1, -1.618f, // K

                -1, 1.618f, 0, // A
                -1.618f, 0, 1, // E
                0, 1, 1.618f, // L

                -1.618f, 0, 1, // E
                -1, 1.618f, 0, // A
                -1.618f, 0, -1, // H

                -1, 1.618f, 0, // A
                0, 1, -1.618f, // K
                -1.618f, 0, -1, // H

                0, 1, -1.618f, // K
                1, 1.618f, 0, // B
                1.618f, 0, -1, // G

                1, 1.618f, 0, // B
                1.618f, 0, 1, // F
                1.618f, 0, -1, // G

                0, 1, 1.618f, // L
                1.618f, 0, 1, // F
                1, 1.618f, 0, // B

                -1.618f, 0, 1, // E
                0, -1, 1.618f, // I
                0, 1, 1.618f, // L

                0, 1, 1.618f, // L
                0, -1, 1.618f, // I
                1.618f, 0, 1, // F

                -1, -1.618f, 0, // D
                -1.618f, 0, 1, // E
                -1.618f, 0, -1, // H

                -1, -1.618f, 0, // D
                0, -1, 1.618f, // I
                -1.618f, 0, 1, // E

                1.618f, 0, 1, // F
                0, -1, 1.618f, // I
                1, -1.618f, 0, // C

                1.618f, 0, 1, // F
                1, -1.618f, 0, // C
                1.618f, 0, -1, // G

                1.618f, 0, -1, // G
                1, -1.618f, 0, // C
                0, -1, -1.618f, // J

                0, 1, -1.618f, // K
                0, -1, -1.618f, // J
                -1.618f, 0, -1, // H

                -1, -1.618f, 0, // D
                -1.618f, 0, -1, // H
                0, -1, -1.618f, // J

                0, -1, -1.618f, // J
                1, -1.618f, 0, // C
                -1, -1.618f, 0, // D

                0, 1, -1.618f, // K
                1.618f, 0, -1, // G
                0, -1, -1.618f, // J

                0, -1, 1.618f, // I
                -1, -1.618f, 0, // D
                1, -1.618f, 0, // C

        };

        uvCoordinates = new float[]{
                // 20
                0.05f, 0,
                0.0f, 0.5f,
                0.1f, 0.5f,

                //19
                0.15f, 0,
                0.1f, 0.5f,
                0.2f, 0.5f,

                //18
                0.25f, 0,
                0.2f, 0.5f,
                0.3f, 0.5f,

                //17
                0.35f, 0,
                0.3f, 0.5f,
                0.4f, 0.5f,

                //16
                0.45f, 0,
                0.4f, 0.5f,
                0.5f, 0.5f,

                //15
                0.55f, 0,
                0.5f, 0.5f,
                0.6f, 0.5f,

                //14
                0.65f, 0,
                0.6f, 0.5f,
                0.7f, 0.5f,

                //13
                0.75f, 0,
                0.7f, 0.5f,
                0.8f, 0.5f,

                //12
                0.85f, 0,
                0.8f, 0.5f,
                0.9f, 0.5f,

                //11
                0.95f, 0,
                0.9f, 0.5f,
                1f, 0.5f,

                //1
                0.05f, 0.5f,
                0, 1,
                0.1f, 1,

                //2
                0.15f, 0.5f,
                0.1f, 1,
                0.2f, 1,

                //3
                0.25f, 0.5f,
                0.2f, 1,
                0.3f, 1,

                //4
                0.35f, 0.5f,
                0.3f, 1,
                0.4f, 1,

                //5
                0.45f, 0.5f,
                0.4f, 1,
                0.5f, 1,

                //6
                0.55f, 0.5f,
                0.5f, 1,
                0.6f, 1,

                //7
                0.65f, 0.5f,
                0.6f, 1,
                0.7f, 1,

                //8
                0.75f, 0.5f,
                0.7f, 1,
                0.8f, 1,

                //9
                0.85f, 0.5f,
                0.8f, 1,
                0.9f, 1,

                //10
                0.95f, 0.5f,
                0.9f, 1,
                1f, 1,
        };

        // Edges around specular light are vanishing , normal vectors are calculated from center (not fully correct)
        normalVectors = verticesCoordinates;

        // Would calculate normal vectors so that edges around specular light are not vanishing.
        // Method for calculating turned off, because calculations are wrong.
//         normalVectors = new float[verticesCoordinates.length];
//         calculateNormalVectors(verticesCoordinates);
    }

    @Override
    public void updateEntity() {
        modelMat = new Matrix4();
        if (duration > 0 && (angle > 0 || yPeak > 0)) {
            bounceFrame++;
            bounce(bounceFrame);
            duration -= 0.3f;
            duration = Math.max(0, duration);
            yPeak -= 0.03f;
            yPeak = Math.max(0, yPeak);
            angle -= 1.2f;
            angle = Math.max(0, angle);
        } else {
            modelMat.translate(0, -2, 0);
            bounceFrame = 0;
        }

        frame++;
    }

    @Override
    protected void renderEntity(int loc, int verticesCount, float startPosX, float startPosY, float startPosZ) {
        super.renderEntity(loc, verticesCount, startPosX, startPosY, startPosZ);

        // Press space to roll the dice
        if (Input.keys[GLFW_KEY_SPACE] && frame > lastUpdateBounce + loop) {
            if (firstAnimation){
                loop = 380;
            }
            firstAnimation = false;
            createVboId(convertShuffleArray(uvCoordinates), 1, 2);
            glBindTexture(GL_TEXTURE_2D, srcTexture.getId());
            duration = 200;
//            yPeak = 10;
            yPeak = 6 + Math.round(Math.random() * 8);
//            angle = 400;
            angle = 300 + Math.round(Math.random() * 200);
            lastUpdateBounce = frame;
        }

        // Use right arrow to change color of the dice
        if(Input.keys[GLFW_KEY_LEFT] && frame > lastUpdateColorChange + 30){
            changeTexture(++srcTextureListIterator);
            lastUpdateColorChange = frame;
        }
    }

    private void bounce(float frame) {
        float yStart = -2;
        if (frame % duration < duration / 2) {
            modelMat.rotateX(angle).rotateY(angle).rotateZ(angle).translate(0, Easing.quintEaseOut(frame % duration, yStart, yPeak, duration), 0);
        } else if (frame % duration >= duration / 2) {
            modelMat.rotateX(angle).rotateY(angle).rotateZ(angle).translate(0, Easing.quintEaseIn(frame % duration, yStart + yPeak, -yPeak, duration), 0);
        }
    }

    @Override
    protected void calculateNormalVectors(float[] verticesCoordinates) {
        super.calculateNormalVectors(verticesCoordinates);
    }

    float[] convertShuffleArray(float[] array) {
        // convert array to compact input array
        float[][] compact = new float[20][6];
        for (int i = 0; i < array.length; i += 6) {
            System.arraycopy(array, i, compact[i / 6], 0, 6);
        }

        // shuffle compact array
        for (int i = 0; i < compact.length; i++) {
            int randomPart = i + random.nextInt(compact.length - i);
            float[] randomElement = compact[randomPart];
            compact[randomPart] = compact[i];
            compact[i] = randomElement;
        }

        // convert compact array back to output array
        for (int i = 0; i < array.length; i += 6) {
            System.arraycopy(compact[i / 6], 0, array, i, 6);
        }
        return array;
    }

}
