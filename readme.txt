Run:
javac LDES.java
java LDES encrypt 1111 11
<<< or >>>
java LDES decrypt 1111 11

To encrypt / decrypt, the number of length 4 is the message and the other one is the key.


For MDES:
Run javac MDES.java
java MDES encrypt 1111 11
<<< or >>>
java MDES encrypt 1111 11

So basically it is exactly the same, there is a work in progress file called MDES3.java which implements ECB / CBC like asked for in the question and is run using the commands specified in the assignment specification.
