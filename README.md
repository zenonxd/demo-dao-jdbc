PONTOS IMPORTANTES A SEREM LEMBRADOS.
Quando usamos o get so resultset é para pegarmos dado da tabela SQL. Se coluna que queremos é a coluna de Id, então será um rs.getInt(nome da coluna).


Para cada entidade do negocio, existirá um objeto responsável por fazer acesso a dados relacionados a esta entidade.
Exemplo:

	• Cliente: ClienteDao
	• Produto: ProdutoDao
	• Pedido: PedidoDao

Cada DAO será definido por uma interface. Isso porque o acesso aos dados pode mudar no futuro. Hoje pode ser um banco de dados SQL e amanhã será Oracle.
Então para que o contrato seja preservado, usaremos interfaces.

A injeção de dependência pode ser feita por meio do padrã de projeto Factory. 
O objeto Factory é responsável por instanciar as implementaçoes do DAO. Ela tem operações estaticas para instanciar os DAOS.

![image](https://github.com/zenonxd/demo-dao-jdbc/assets/64092861/b28fd55c-5ef2-487c-948c-3412d7956f67)


PRIMEIRA PARTE
Criar Seller e Department, juntamente com seus atributos, construtores, etc. Conforme abaixo:
![image](https://github.com/zenonxd/demo-dao-jdbc/assets/64092861/328ce494-98aa-4093-8887-554ca2b43db1)

SEGUNDA PARTE - DepartmentDao and SellerDao interfaces

A unica diferença é: No SellerDao os dois ultimos são objetos do tipo Seller. E no DepartmentDao do tipo Department.

![image](https://github.com/zenonxd/demo-dao-jdbc/assets/64092861/8464eab7-76a8-44b7-ab73-83081932fe37) ![image](https://github.com/zenonxd/demo-dao-jdbc/assets/64092861/98e0a0d4-8498-4573-97dd-c09c655ecbe5)

TERCEIRA PARTE - SellerDaoJDBC and DaoFactory

SellerDaoJDBC - Implementar a interface SellerDao com seu contrato. ![image](https://github.com/zenonxd/demo-dao-jdbc/assets/64092861/c674acb8-4e27-4bf1-b83d-b6ce315acfd6)

DaoFactory - A DaoFactory vai expor um metodo que retorna um tipo da interface. Mas internamente ela instancia uma implementação. 

![image](https://github.com/zenonxd/demo-dao-jdbc/assets/64092861/c9106ec0-9d23-4ae0-81ae-6310638786bc)

Então no programa principal, instanciamos a interface juntamente com a DaoFactory.

![image](https://github.com/zenonxd/demo-dao-jdbc/assets/64092861/e0f18384-9a49-49fe-ba5c-2207f72dbe50)

//

IMPLEMENTANDO MÉTODO FindById

Primeiro, não precisamos dessa vez instanciar uma connection, uma vez que o DAO vai ter uma dependência com a conexão. Portanto: 
Voltando a classe SellerDaoJDBC
1. Criamos um atributo
   
![image](https://github.com/zenonxd/demo-dao-jdbc/assets/64092861/c8dfd7c0-6ad5-48e2-8507-9c4b0ae91557)

2.	Criamos um construtor passando uma Connection conn, e atribuindo a esse paremetro o conn inicial. É o atributo inicial que usamos dentro da SellerDaoJDBC.

![image](https://github.com/zenonxd/demo-dao-jdbc/assets/64092861/79cfe387-4278-4776-b5c6-974247510425)

IMPORTANTE! Sabemos que o ResultSet nos dá um retorno em formato de tabela, conforme abaixo:
![image](https://github.com/zenonxd/demo-dao-jdbc/assets/64092861/4519fb33-1c12-4bf5-aa1b-62422c2c1729)

Porém, quando estamos programando um sistema orientado a objetos, na memoria do computador, queremos ter os objetos associados instanciados em memória.

![image](https://github.com/zenonxd/demo-dao-jdbc/assets/64092861/482db49f-9bfd-4554-bdc9-bc02e509789a)

Em suma, queremos criar um objeto do tipo seller chamado alex que estará associado a outro objeto do tipo Department, com os dados do departamento do alex.

DENTRO DO METODO FindById:

1. Criamos nosso PreparedStatement e ResultSet, intanciando ambos em null.
2. Abrimos nosso bloco Try, passando nosso st e utilizando o "conn" prepareStatement.
3. Passamos a query escrita para dar o select e como sempre a " ? " para preenchermos abaixo.
   
![image](https://github.com/zenonxd/demo-dao-jdbc/assets/64092861/3f935c35-a45d-48e9-ac1d-14257d7c355e)

5. Passamos st.SetInt e preenchemos com o numero desejado e executamos o update.

![image](https://github.com/zenonxd/demo-dao-jdbc/assets/64092861/9d8f72b9-a464-4e6d-90ea-59bf50491e87)

// COMO INSTANCIAR ESSES DADOS DO SQL DENTRO DOS OBJETOS?

![image](https://github.com/zenonxd/demo-dao-jdbc/assets/64092861/4e12b3c4-9156-4aa8-96f4-e929fe819801)

No bloco if em questão, sabemos que ele só irá prosseguir (true) se houver um numero depois do next (que já começa em zero).
Caso sim:
1. instanciaremos um Department dep e daremos o set no Id e Name do objeto.
2. o getInt e getString é a coluna da tabela criada no WorkBench. Ou seja: Int porque irá retornar uma ID da tabela e String pois é retornado um nome.
3. SEMPRE QUE DAMOS GET no resultset é pra pegar algo de dentro do SQL.
!!! ESSE setId é do objeto e não um set SQL !!!

Depois de settar o Id e Nome do nosso objeto, instanciaremos um seller.

E faremos a mesma coisa, daremos o set no objeto dando o get da tabela SQL.

!!!Novamente, estamos settando dentro do objeto o que vem da tabela, portanto setId(rs.getint)!!! rs.get pega dentro do SQL

Porém na hora de dar o setDepartment, não passamos o get e sim o Department criado ali em cima. <<<

Damos o return obj (seller)

![image](https://github.com/zenonxd/demo-dao-jdbc/assets/64092861/b9bcf842-f9c4-489c-9604-2f86ec44fdff)

Após isso, fazer um return null caso o if inicial de false.

