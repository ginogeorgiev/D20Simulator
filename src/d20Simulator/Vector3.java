package d20Simulator;

@SuppressWarnings("unused")
public class Vector3 {

    private float[] vector3;

    public Vector3() {
        vector3 = new float[] {1f, 1f , 1f};
    }

    public Vector3(float x, float y, float z) {
        vector3 = new float[] {x, y, z};
    }

    public Vector3( Vector3 copy){
        this.vector3 = copy.vector3.clone();
    }

    public float getX(){
        return vector3[0];
    }

    public float getY(){
        return vector3[1];
    }

    public float getZ(){
        return vector3[2];
    }

    public float dot(Vector3 other){
        return this.vector3[0] * other.vector3[0] +
               this.vector3[1] * other.vector3[1] +
               this.vector3[2] * other.vector3[2];
    }

    public Vector3 cross(Vector3 other) {
        float[] vec = new float[3];

        vec[0] = this.vector3[1] * other.vector3[2] - this.vector3[2] * other.vector3[1];
        vec[1] = this.vector3[2] * other.vector3[0] - this.vector3[0] * other.vector3[2];
        vec[2] = this.vector3[0] * other.vector3[1] - this.vector3[1] * other.vector3[0];

        this.vector3 = vec;
        return this;
    }

    public Vector3 subtract(Vector3 other){
        float[] vec = new float[3];

        vec[0] = this.vector3[0] - other.vector3[0];
        vec[1] = this.vector3[1] - other.vector3[1];
        vec[2] = this.vector3[2] - other.vector3[2];

        this.vector3 = vec;
        return this;
    }
}
