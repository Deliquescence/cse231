package exercise.ch09;

class PriQOverflowException extends RuntimeException
{
  public PriQOverflowException()
  {
    super();
  }

  public PriQOverflowException(String message)
  {
    super(message);
  }
}