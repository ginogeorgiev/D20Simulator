package d20Simulator;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public abstract class Entity {

    protected Matrix4 modelMat;
    protected int verticesCount;
    protected int vaoId;
    protected Texture srcTexture;

    protected float[] verticesCoordinates;
    protected float[] uvCoordinates;
    protected float[] normalVectors;

    protected String[] srcTextureList;
    protected int srcTextureListIterator;
    protected int frame;
    protected int lastUpdateColorChange = frame;

    protected int getVerticesCount(){
        return this.verticesCount;
    }

    /**
     * Prepares vao with vbo for fragment-shader
     */
    protected void initEntity(int srcTextureListIterator){
        vaoId = glGenVertexArrays();
        glBindVertexArray(vaoId);

        createVboId(verticesCoordinates, 0, 3);
        createVboId(uvCoordinates, 1, 2);
        createVboId(normalVectors, 2, 3);

        this.srcTextureListIterator = srcTextureListIterator  % srcTextureList.length;
        srcTexture = new Texture(srcTextureList[srcTextureListIterator]);
    }

    protected abstract void updateEntity();

    protected void renderEntity(int loc, int verticesCount, float startPosX, float startPosY, float startPosZ){
        modelMat.translate(startPosX, startPosY, startPosZ);
        glBindVertexArray(vaoId);
        glUniformMatrix4fv(loc, false, modelMat.getValuesAsArray());
        glBindTexture(GL_TEXTURE_2D, srcTexture.getId());
        glDrawArrays(GL_TRIANGLES, 0, verticesCount);
    }

    protected void createVboId(float[] name, int index, int size){
        int vboId = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboId);

        glBufferData(GL_ARRAY_BUFFER, name, GL_STATIC_DRAW);
        glVertexAttribPointer(index, size, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(index);
    }

    protected void changeTexture(int srcTextureListIterator) {
        srcTextureListIterator = srcTextureListIterator % srcTextureList.length;
        srcTexture = new Texture(srcTextureList[srcTextureListIterator]);
        glBindTexture(GL_TEXTURE_2D, srcTexture.getId());
    }

    protected void calculateNormalVectors(float[] verticesCoordinates){
        normalVectors = new float[verticesCoordinates.length];
        for (int i = 0; i < verticesCoordinates.length; i += 9) {
            Vector3 a = new Vector3(normalVectors[i], normalVectors[i + 1], normalVectors[i + 2]);
            Vector3 b = new Vector3(normalVectors[i + 3], normalVectors[i + 4], normalVectors[i + 5]);
            Vector3 c = new Vector3(normalVectors[i + 6], normalVectors[i + 7], normalVectors[i + 8]);

            Vector3 firstLine = new Vector3(a.subtract(b));
            Vector3 secondLine = new Vector3(c.subtract(b));
            Vector3 crossProduct = new Vector3(firstLine.cross(secondLine));
            for (int j = 0; j < 9; j += 3) {
                normalVectors[j] = crossProduct.getX();
                normalVectors[j + 1] = crossProduct.getY();
                normalVectors[j + 1] = crossProduct.getZ();
            }
        }
    }
}
