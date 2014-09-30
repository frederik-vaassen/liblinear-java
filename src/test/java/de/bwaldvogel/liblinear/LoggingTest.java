package de.bwaldvogel.liblinear;

import org.junit.Test;

import java.io.IOException;

/**
 * Created by Frederik Vaassen (frederik.vaassen@stepstone.be) on 26/09/2014.
 */
public class LoggingTest {

    private final int instance_indices[][] = {
            new int[] {1, 2, 3},
            new int[] {1, 3},
            new int[] {1, 2}
    };
    private final double instance_values[][] = {
            new double[] {0.33,0.10,0.20},
            new double[] {0.75,0.66},
            new double[] {1.0,0.95}
    };
    private final double labels[] = {
            -1.0,
            1.0,
            1.0
    };
    private final Parameter parameter = new Parameter(
            SolverType.L2R_L2LOSS_SVC,
            1.0,
            0.01
    );

    public Feature[] makeNodes(int[] indices, double[] values) {

        Feature[] nodes = new Feature[indices.length];

        int i = 0;
        for (int j = 0; i < indices.length; j++) {
            final int index = indices[j];
            final double value = values[j];
            nodes[i] = new FeatureNode(index, value);
            i++;
        }

        return nodes;
    }

    public Problem getDummyProblem() {

        Problem problem = new Problem();
        problem.l = 3;
        problem.n = 3;
        problem.y = labels;
        problem.x = new Feature[problem.l][problem.n];

        for (int i = 0; i < instance_indices.length; i++) {
            problem.x[i] = makeNodes(instance_indices[i], instance_values[i]);
        }

        return problem;
    }

    @Test
    public void testDefaultLogger() {
        while (true) {
            final Model model = Linear.train(getDummyProblem(), parameter);
        }
    }

    @Test
    public void testDisabledLogger() throws IOException {

        Linear.disableDebugOutput();
        final Model model = Linear.train(getDummyProblem(), parameter);
    }

    @Test
    public void testEnabledLogger() throws IOException {

        Linear.disableDebugOutput();
        Linear.enableDebugOutput();
        final Model model = Linear.train(getDummyProblem(), parameter);
    }

}
