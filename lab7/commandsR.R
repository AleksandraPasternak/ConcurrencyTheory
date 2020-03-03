results = read.csv("C:/Users/paste/OneDrive/Documents/tw/Teoriawspolbieznosci/lab7/times.csv", header=FALSE, sep = " ")
result5 <- results[results$V2==5,]
result15 <- results[results$V2==15,]
result30 <- results[results$V2==30,]

p <- ggplot(result15, aes(V3, V4, group=V3))+ xlab("Numer filozofa")+ ylab("Czas oczekiwania w milisekundach")
q<- p+ geom_boxplot() 
r<-q+ stat_summary(fun.y=mean, geom="point", shape=20, size=2, color="red", fill="red")
r+facet_wrap(~V1)
