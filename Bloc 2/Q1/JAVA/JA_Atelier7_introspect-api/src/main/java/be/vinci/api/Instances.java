package be.vinci.api;

import be.vinci.classes.User;
import be.vinci.instances.InstanceGraph1;
import be.vinci.services.ClassAnalyzer;
import be.vinci.services.InstancesAnalyzer;
import jakarta.json.JsonStructure;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Send instances graph data to make object diagrams
 *
 * The instances graphs are initialized by a class containing the "initInstanceGraph" method,
 * building the instance graph, and returning it.
 *
 * The "instance builder class name" must be given and present into the "instances" package
 */
@Path("instances")
public class Instances {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonStructure getInstanceGraphInfo(@QueryParam("builderclassname") String builderClassname) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object object;
        try {
            Class builder = Class.forName("be.vinci.instances." + builderClassname);
            System.out.println( "class : " + builder );
            Constructor constructor = builder.getDeclaredConstructor();
            System.out.println( "contructor : " + constructor );
            object = constructor.newInstance();
            System.out.println( "Object : " + object );
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        // InstanceGraph1 builder = new InstanceGraph1();    // TODO change this line to use the query parameter, and generate dynamically the builder
        Object instanceObject = object;  // TODO change this line to avoid calling initInstanceGraph() directly
        Class instanceClass = object.getClass();
        Method instanceMethod = instanceClass.getMethod("initInstanceGraph");
        InstancesAnalyzer instanceAnalyzer = (InstancesAnalyzer) instanceMethod.invoke(instanceObject);
        return instanceAnalyzer.getFullInfo();
    }
}
