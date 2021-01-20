#version 330

layout(location = 0) in vec3 cornerVerticesFromJava;
layout(location = 1) in vec2 textureFromJava;
layout(location = 2) in vec3 normalVecsFromJava;

uniform mat4 mMat;
uniform mat4 pMat;

mat3 nMat;

out vec3 nVec;
out vec3 position;
out vec2 uvCoords;

void main() {

    nMat = inverse(transpose(mat3(mMat)));
    nVec =  nMat * normalVecsFromJava;
    
    position = vec3((mMat) * vec4(cornerVerticesFromJava,1));

    gl_Position = pMat * mMat * vec4(cornerVerticesFromJava,1);
    uvCoords = textureFromJava;
}