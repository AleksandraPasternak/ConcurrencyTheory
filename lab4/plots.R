library(ggplot2)
library(ggpubr)
theme_set(theme_pubr())

results = read.csv("C:/Users/paste/IdeaProjects/Teoriawspolbieznosci/lab4/NaiveProduction/times9.csv", header=FALSE, sep = " ")
avg_results = aggregate(V3 ~ V2:V1, data=results, FUN=mean)
first <- ggplot(avg_results, aes(V2, V3, color=V1)) + ylab("Czas oczekiwania na dostêp do bufora[ms]") + xlab("Wielkoœæ porcji") + geom_point()


results2 = read.csv("C:/Users/paste/IdeaProjects/Teoriawspolbieznosci/lab4/FairProduction/times9.csv", header=FALSE, sep = " ")
avg_results2 = aggregate(V3 ~ V2:V1, data=results2, FUN=mean)
second <- ggplot(avg_results2, aes(V2, V3, color=V1)) + ylab("Czas oczekiwania na dostêp do bufora[ms]") + xlab("Wielkoœæ porcji") + geom_point()


figure <- ggarrange(first, second,labels = c("naive", "fair"),ncol = 2, nrow = 1)
figure