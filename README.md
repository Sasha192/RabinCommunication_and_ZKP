# Rabin Communication and Zero Knowledge Protocol

* [Project purpose](#purpose)
* [Teck stack](#structure)
* [Authors](#authors)
* [Bibliography](#bibliography)

# <a name="purpose"></a>Project purpose

Let : 
* public Modulus ( publicMod ) = <img src = "https://render.githubusercontent.com/render/math?math=%5C%20n%20%5C%20%3A%20%5C%20n%20%3D%20p%20*%20q">
, that p and q - big prime numbers with more than 256 bits.
* <img src = "https://render.githubusercontent.com/render/math?math=%5C%20RABIN%20%3A%20M%20%5Crightarrow%20C"> , where M - all messages, C - all cipher texts, RABIN - encryption function.
* <img src="https://render.githubusercontent.com/render/math?math=%5Cbinom%7Bx%7D%7Bn%7D%20-%20Jacobi%20%5C%20symbol" >


Understanding specificity of Rabin cryptosystem: 

* Correctness of implementation was tested with remote server ( see class Server, ServerService )

# <a name="structure"></a>Tech stack
* Java 8
* Maven 4.0.0
* JUnit 4
* JSON-Simple 1.1



# <a name="authors"></a>Authors
* [Oleksandr Bunin](https://github.com/Sasha192)

# <a name="bibliography"></a>Bibliography

[1] Glenn Durfee. Cryptanalysis of RSA using Algebraic and Lattice methods.
A dissertation submitted to the department of Computer Science and the committee on graduate studies of Stanford university in partial fulfillment of the requirements
 for the degree of Doctor of Philosophy, 2002.
 
[2] M. Bellare and P. Rogaway. Optimal asymmetric encryption. In proceedings
Eurocrypt ’94, Lecture Notes in Computer Science, vol. 950, Springer-Verlag,
pp. 92–111, 1994.

[3] D. Boneh. Twenty years of attacks on the RSA cryptosystem. Notices of
the AMS, 46(2):203–213, 1999

[4] D. Coppersmith, M. Franklin, J. Patarin, and M. Reiter. Low exponent
RSA with related messages. In proceedings Eurocrypt ’96, Lecture Notes in
Computer Science, vol. 1070, Springer-Verlag, pp. 1–9, 1996

[5] Rivest R., Shamir A., Adleman L. A method for obtaining digital signatures and public-key cryptosystems. ACM — New York City: ACM, 1978. — Vol. 21, Iss. 2. — P. 120–126.