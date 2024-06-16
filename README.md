PONTOS IMPORTANTES A SEREM LEMBRADOS.
### Quando usamos o get do resultset é para pegarmos dado da tabela SQL. Se coluna que queremos é a coluna de Id, então será um rs.getInt(nome da coluna). ###


Para cada entidade do negocio, existirá um objeto responsável por fazer acesso a dados relacionados a esta entidade.
Exemplo:

	• Cliente: ClienteDao
	• Produto: ProdutoDao
	• Pedido: PedidoDao

<details>
	<summary>DAO</summary>
Cada DAO será definido por uma interface. Isso porque o acesso aos dados pode mudar no futuro. Hoje pode ser um banco de dados SQL e amanhã será Oracle.
Então para que o contrato seja preservado, usaremos interfaces.

A injeção de dependência pode ser feita por meio do padrã de projeto Factory. 
O objeto Factory é responsável por instanciar as implementaçoes do DAO. Ela tem operações estaticas para instanciar os DAOS.

![image](https://github.com/zenonxd/demo-dao-jdbc/assets/64092861/b28fd55c-5ef2-487c-948c-3412d7956f67)
</details>

<h1 align="center">ETAPAS DO PROJETO </h1>
<details>
	<summary>PRIMEIRA PARTE - CRIAÇÃO DE CLASSES E TO-DO </summary>
Criar Seller e Department, juntamente com seus atributos, construtores, etc. Conforme abaixo:
	
![image](https://github.com/zenonxd/demo-dao-jdbc/assets/64092861/328ce494-98aa-4093-8887-554ca2b43db1)
</details>

<details>
	<summary>SEGUNDA PARTE - Criando Interfaces, DepartmentDao and SellerDao </summary>

A unica diferença é: No SellerDao os dois ultimos são objetos do tipo Seller. E no DepartmentDao do tipo Department.

![image](https://github.com/zenonxd/demo-dao-jdbc/assets/64092861/8464eab7-76a8-44b7-ab73-83081932fe37) ![image](https://github.com/zenonxd/demo-dao-jdbc/assets/64092861/98e0a0d4-8498-4573-97dd-c09c655ecbe5)
</details>

<details>
	<summary>TERCEIRA PARTE - SellerDaoJDBC and DaoFactory</summary>

SellerDaoJDBC - Implementar a interface SellerDao com seu contrato. ![image](https://github.com/zenonxd/demo-dao-jdbc/assets/64092861/c674acb8-4e27-4bf1-b83d-b6ce315acfd6)

DaoFactory - A DaoFactory vai expor um metodo que retorna um tipo da interface. Mas internamente ela instancia uma implementação. 

![image](https://github.com/zenonxd/demo-dao-jdbc/assets/64092861/c9106ec0-9d23-4ae0-81ae-6310638786bc)

Então no programa principal, instanciamos a interface juntamente com a DaoFactory.

![image](https://github.com/zenonxd/demo-dao-jdbc/assets/64092861/e0f18384-9a49-49fe-ba5c-2207f72dbe50)

</details>

<h1 align="center">IMPLEMENTANDO MÉTODO FindById </h1>
<h2 align="center"><a href=https://github.com/zenonxd/demo-dao-jdbc/blob/f3aaf7e782e855f55d9b806e4fd8497dbd928761/src/model/dao/impl/SellerDaoJDBC.java#L42> Para visualizar o que foi feito abaixo clique aqui! </h2>
	
	 
<DETAILS>
	<SUMMARY>findById implementation</SUMMARY>
	
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

	1. Criamos duas funçoes. Uma para o Seller e outra para o Department.
		a. Na função do Seller, teremos dois parametros (Resultset rs, Department dep)
		b. Na função deparment, somente o parametro do ResultSet
  
<h3> A função instantiateSeller recebe dois parâmetros pois no final da mesma, devemos instanciar também um Department. </h3>

	2. O getInt e getString é a coluna da tabela criada no WorkBench. Ou seja: Int porque irá retornar uma ID da tabela e String pois é retornado um nome. <br>
 	3. SEMPRE QUE DAMOS GET no resultset é pra pegar algo de dentro do SQL. !!! ESSE setId é do objeto e não um set SQL !!!
	
![image](https://github.com/zenonxd/demo-dao-jdbc/assets/64092861/65c95e2b-ad4b-4a78-a68c-4af66e9ae711)

Por fim, nosso if ficará dessa maneira:

![image](https://github.com/zenonxd/demo-dao-jdbc/assets/64092861/7df73626-a091-4cd3-a589-b91a94e68306)

Teste final no main:

![image](https://github.com/zenonxd/demo-dao-jdbc/assets/64092861/05732275-ebf7-4a7a-b151-77042c0cc36d)

</DETAILS>

<h1 align="center">IMPLEMENTANDO MÉTODO FindByDepartment </h1>
<h2 align="center"><a href=https://github.com/zenonxd/demo-dao-jdbc/blob/f3aaf7e782e855f55d9b806e4fd8497dbd928761/src/model/dao/impl/SellerDaoJDBC.java#L97> Para visualizar o que foi feito abaixo clique aqui! </h2>

<DETAILS>
	<SUMMARY>findByDepartment Implementation</SUMMARY>
	
Primeiramente, é incorreto nós instanciarmos vários departamentos.
Dessa vez, como são vários valores retornados ao invés de if dentro do método, usaremos while Ou seja, "enquanto existir um próximo (rs.next)".

1. Iniciaremos criando uma Lista fora do loop while.
   
![image](https://github.com/zenonxd/demo-dao-jdbc/assets/64092861/98d548ea-25a0-46b3-9dd0-5be1179b71c1)

A resolução parece boa, mas na verdade é uma má prática pois intanciaremos vários departments.

![image](https://github.com/zenonxd/demo-dao-jdbc/assets/64092861/dcd40f17-e9fb-40d8-9db8-3d1826eb47a2)

<h3> Para uma boa pratica, usaremos o Map! </h3>

1. Criaremos um map fora do laço while.
2. Dentro do laço while:
	a. Criaremos um Department, dando o get na Coluna: Department dep = map.get(rs.getInt("DepartmentId"));
           Basicamente esse comando busca dentro do Map se ja existe algum departamento com a Id = 2 (valor que está dentro do resultset (rs.next)
   
3. Se o dep == null, instanciaremos o dep e colocaremos dentro do Map para que na proxima vez eu possa verificar que ele ja existe.
   
![image](https://github.com/zenonxd/demo-dao-jdbc/assets/64092861/ac721930-12ac-4232-b9ec-e7c34b2801b0)

4. Por fim, teremos a instanciação do Seller, passando o rs + dep e adicionando a lista.

![image](https://github.com/zenonxd/demo-dao-jdbc/assets/64092861/1d117112-e033-4fbe-8d7a-970918f1e362)

Teste final no main:

![image](https://github.com/zenonxd/demo-dao-jdbc/assets/64092861/46f7da24-2e4a-4d65-b12c-a6581b9915ca)

 
</DETAILS>

<h1 align="center">IMPLEMENTANDO MÉTODO FindAll </h1>
<h2 align="center"><a href=https://github.com/zenonxd/demo-dao-jdbc/blob/ba7a302a0a26b238a7f2b6faec21108966912c57/src/model/dao/impl/SellerDaoJDBC.java#L89> Para visualizar o que foi feito abaixo clique aqui! </h2>

<DETAILS>
	<SUMMARY>findAll Implementation</SUMMARY>
	
Será a mesma estrutura do findByDepartment!

Só trocaremos a query digitada e não precisaremos passar o st.setInt pois dessa o vez o select é sem where.

![image](https://github.com/zenonxd/demo-dao-jdbc/assets/64092861/91289379-cdd7-493f-a683-a958c1e48d4d)

Implementação no programa inicial:

 ![image](https://github.com/zenonxd/demo-dao-jdbc/assets/64092861/e031c200-4a45-4eaa-8858-475741e7aeeb)

</DETAILS>


