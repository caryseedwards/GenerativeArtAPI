package strategyandfactory.parameters;

/**
 * The parent parameter class used to standarise all types of parameters.
 * Part of the template pattern
 * @author carysedwards
 */
public abstract class Parameters {
    protected String parameterType;
    public abstract boolean validateParameters();
}