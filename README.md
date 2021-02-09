# Data Engineer Challenge
disclaimer -> A solução ideal seria usando UDAF agregando os 3 tipos ao mesmo tempo. Embora com o uso de UDAF o processamento seja mais custoso, a velocidade e implementação compensa. 
O uso não foi feito no projeto em questão por motivos de tempo, mas a ideia pode ser entendida em: https://github.com/DPAquino/Scala_Spark

# Requisitos

Technology | Version
------- | --------
Scala | 2.11
Colossus | 0.9.0
Spark | 2.2.0
SBT   | 1.4.7


## Instruções de como Configurar e Executar a aplicação

Em "Resources", temos o arquivo de configuração [application.conf] com o path onde os arquivos jsons deverão ficar: 

```
Configuration {
    Env {
        Json_SourceFolder="""/home/XXXXXXXXX/Projeto/Dados""",
       
    }
}
```  

## Rest Api's

**API que testa se os serviços estão ativos**
```
http://localhost:9000/test
```

**API Principal na qual deverá ser executada e trazer os resultados conforme os filtros.**
```
# http://localhost:9000/get/mapping/{type}

Exemplo: http://localhost:9000/get/mapping/by-os
```


#####Filtro de Sessionization por Device
```
Exemplo: http://localhost:9000/get/mapping/by-device
```

#####Filtro de Sessionization por Browser
```
Exemplo: http://localhost:9000/get/mapping/by-browser
```

#####Filtro de Sessionization por OS
```
Exemplo: http://localhost:9000/get/mapping/by-os
```


# Communication

Contact the developer:
[tag.utc@gmail.com](mailto:tag.utc@gmail.com)