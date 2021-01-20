#version 330

uniform sampler2D smplr;

in vec2 uvCoords;
in vec3 nVec;
in vec3 position;

vec3 lightSource = vec3(-5,5,5);
//vec3 lightSource = vec3(0,0,0);

float lightIntensity = 1.3f;
float specularReflectionConstant = 0.1;
float diffuseReflectionConstant = 0.8;
float ambientReflectionConstant = 0.1;
float shininessConstant = 30;

out vec3 pixelColor;

void main(){

    vec4 texel = texture(smplr, uvCoords);

    // prepare Phong-shading formula parameters
    vec3 normalVec = normalize(nVec);
    vec3 lightVec = normalize(lightSource-position);
    vec3 reflectionVec = reflect(-lightVec,normalVec);
    vec3 viewVec = normalize(-position);

    float diffuseLight = max(0,dot(normalVec,lightVec));
    float shininess = pow(max(0,dot(reflectionVec,viewVec)),shininessConstant);

    // Phong-shading formula
    pixelColor = vec3(texel) * ambientReflectionConstant + lightIntensity * (diffuseLight * vec3(texel) * diffuseReflectionConstant + shininess);
}