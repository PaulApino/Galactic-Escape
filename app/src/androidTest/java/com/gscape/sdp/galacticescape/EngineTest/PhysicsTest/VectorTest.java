package com.gscape.sdp.galacticescape.EngineTest.PhysicsTest;

import com.gscape.sdp.galacticescape.Engine.Physics.Vector;

import org.junit.Test;
import java.util.Random;

import static org.junit.Assert.*;

public class VectorTest {

    private Random rand = new Random();

    @Test
    public void vectorMake2D () {
        for (int i = 0; i < 100000; i++) {
            double x = rand.nextInt();
            double y = rand.nextInt();
            Vector v1 = Vector.make2D(x, y);
            assertEquals(x, v1.getX(), 0);
            assertEquals(y, v1.getY(), 0);
        }
    }

    @Test
    public void vectorMake3D () {
        for (int i = 0; i < 100000; i++) {
            double x = rand.nextInt();
            double y = rand.nextInt();
            double z = rand.nextInt();
            Vector v1 = Vector.make3D(x, y, z);
            assertEquals(x, v1.getX(), 0);
            assertEquals(y, v1.getY(), 0);
            assertEquals(z, v1.getZ(), 0);
        }
    }

    @Test
    public void vectorEquals () {
        for (int i = 0; i < 100000; i++) {
            double x = rand.nextInt();
            double y = rand.nextInt();
            double z = rand.nextInt();
            Vector v1 = Vector.make3D(x, y, z);
            Vector v2 = Vector.make3D(x, y, z);
            assertTrue(v1.equals(v2));
        }
    }

    @Test
    public void vectorMagnitude () {
        for (int i = 0; i < 100000; i++) {
            double x = rand.nextInt();
            double y = rand.nextInt();
            double z = rand.nextInt();
            double magnitude = Math.sqrt((x * x) + (y * y) + (z * z));
            Vector v1 = Vector.make3D(x, y, z);
            assertEquals(magnitude, v1.getMagnitude(), 0);
        }
    }

    @Test
    public void vectorAdd () {
        for (int i = 0; i < 100000; i++) {
            double x1 = rand.nextInt();
            double x2 = rand.nextInt();
            double y1 = rand.nextInt();
            double y2 = rand.nextInt();
            double z1 = rand.nextInt();
            double z2 = rand.nextInt();
            double xAns = x1 + x2;
            double yAns = y1 + y2;
            double zAns = z1 + z2;
            Vector v1 = Vector.make3D(x1, y1, z1);
            Vector v2 = Vector.make3D(x2, y2, z2);
            Vector vAnsExpected = Vector.make3D(xAns, yAns, zAns);
            Vector vAnsActual = v1.add(v2);
            assertEquals(xAns, vAnsActual.getX(), 0);
            assertEquals(yAns, vAnsActual.getY(), 0);
            assertEquals(zAns, vAnsActual.getZ(), 0);
            assertEquals(vAnsExpected, vAnsActual);
        }
    }

    @Test
    public void vectorMinus () {
        for (int i = 0; i < 100000; i++) {
            double x1 = rand.nextInt();
            double x2 = rand.nextInt();
            double y1 = rand.nextInt();
            double y2 = rand.nextInt();
            double z1 = rand.nextInt();
            double z2 = rand.nextInt();
            double xAns = x1 - x2;
            double yAns = y1 - y2;
            double zAns = z1 - z2;
            Vector v1 = Vector.make3D(x1, y1, z1);
            Vector v2 = Vector.make3D(x2, y2, z2);
            Vector vAnsExpected = Vector.make3D(xAns, yAns, zAns);
            Vector vAnsActual = v1.minus(v2);
            assertEquals(xAns, vAnsActual.getX(), 0);
            assertEquals(yAns, vAnsActual.getY(), 0);
            assertEquals(zAns, vAnsActual.getZ(), 0);
            assertEquals(vAnsExpected, vAnsActual);
        }
    }

    @Test
    public void vectorProjectWith () {
        for (int i = 0; i < 100000; i++) {
            double x1 = rand.nextInt();
            double y1 = rand.nextInt();
            double z1 = rand.nextInt();
            double magnitudeForProjection = Math.sqrt((x1 * x1) + (y1 * y1) + (z1 * z1));
            double magnitudeToProject = rand.nextInt();
            double x2 = magnitudeToProject * (x1 / magnitudeForProjection);
            double y2 = magnitudeToProject * (y1 / magnitudeForProjection);
            double z2 = magnitudeToProject * (z1 / magnitudeForProjection);
            double delta = Math.abs(Math.sqrt((x2 * x2) + (y2 * y2) + (z2 * z2)) - magnitudeToProject);
            Vector v1 = Vector.make3D(x1, y1, z1);
            Vector v2 = v1.projectWith(magnitudeToProject);
            assertEquals(x2, v2.getX(), 0);
            assertEquals(y2, v2.getY(), 0);
            assertEquals(z2, v2.getZ(), 0);
            assertEquals(magnitudeToProject, v2.getMagnitude(), delta);
        }
    }
}
