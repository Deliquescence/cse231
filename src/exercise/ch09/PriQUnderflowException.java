package exercise.ch09;

class PriQUnderflowException extends RuntimeException
{
  public PriQUnderflowException()
  {
    super();
  }

  public PriQUnderflowException(String message)
  {
    super(message);
  }
}