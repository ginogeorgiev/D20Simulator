package d20Simulator;

public class Matrix4 {

	private float[] matrix4;

	public Matrix4() {
		matrix4 = new float[]
				{1f,0f,0f,0f,
				0f,1f,0f,0f,
				0f,0f,1f,0f,
				0f,0f,0f,1f};
	}

	public Matrix4(Matrix4 copy) {
		this.matrix4 = copy.matrix4.clone();
	}

	public Matrix4(float near, float far) {
		matrix4 = new float[]
				{1f,0f,0f,0f,
				0f,1f,0f,0f,
				0f,0f,(-far-near)/(far-near),-1f,
				0f,0f,(-2*near*far)/(far-near),0f};
	}

	public Matrix4 multiply(Matrix4 other) {
		float[] mat = new float[16];
		for (int i = 0; i < 4; i++){
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					mat[i + 4 * j] += other.matrix4[i + k * 4] * this.matrix4[4 * j + k];
				}
			}
		}
		this.matrix4 = mat;
		return this;
	}

	public Matrix4 translate(float x, float y, float z) {
		Matrix4 transMat = new Matrix4();
		transMat.matrix4[12] = x;
		transMat.matrix4[13] = y;
		transMat.matrix4[14] = z;
		this.multiply(transMat);
		return this;
	}

	public Matrix4 scale(float uniformFactor) {
		Matrix4 uniScaleMat = new Matrix4();
		uniScaleMat.matrix4[0] = uniformFactor;
		uniScaleMat.matrix4[5] = uniformFactor;
		uniScaleMat.matrix4[10] = uniformFactor;
		this.multiply(uniScaleMat);
		return this;
	}

	public Matrix4 scale(float sx, float sy, float sz) {
		Matrix4 scaleMat = new Matrix4();
		scaleMat.matrix4[0] = sx;
		scaleMat.matrix4[5] = sy;
		scaleMat.matrix4[10] = sz;
		scaleMat.matrix4[15] = 1;
 		this.multiply(scaleMat);
		return this;
	}

	public Matrix4 rotateX(float angle) {
		angle = (float)(angle * Math.PI / 180);
		Matrix4 rotXMat = new Matrix4();
		float sin = (float)Math.sin(angle);
		float cos = (float)Math.cos(angle);
		rotXMat.matrix4[5] = cos;
		rotXMat.matrix4[6] = sin;
		rotXMat.matrix4[9] = -sin;
		rotXMat.matrix4[10] = cos;
		this.multiply(rotXMat);
		return this;
	}

	public Matrix4 rotateY(float angle) {
		angle = (float)(angle * Math.PI / 180);
		Matrix4 rotYMat = new Matrix4();
		float sin = (float)Math.sin(angle);
		float cos = (float)Math.cos(angle);
		rotYMat.matrix4[0] = cos;
		rotYMat.matrix4[2] = sin;
		rotYMat.matrix4[8] = -sin;
		rotYMat.matrix4[10] = cos;
		this.multiply(rotYMat);
		return this;
	}

	public Matrix4 rotateZ(float angle) {
		angle = (float)(angle * Math.PI / 180);
		Matrix4 rotZMat = new Matrix4();
		float sin = (float)Math.sin(angle);
		float cos = (float)Math.cos(angle);
		rotZMat.matrix4[0] = cos;
		rotZMat.matrix4[1] = sin;
		rotZMat.matrix4[4] = -sin;
		rotZMat.matrix4[5] = cos;
		this.multiply(rotZMat);
		return this;
	}

	public float[] getValuesAsArray() {
		return this.matrix4;
	}
}
