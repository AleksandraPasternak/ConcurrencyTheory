setwd("C:/Users/paste/IdeaProjects/Teoriawspolbieznosci/lab5/Mandelbrot")
results = read.csv("results.csv")
avg_results = aggregate(time ~ threadsNo:tasksNo, data=results, FUN=mean)
avg_results$sd = aggregate(time ~ threadsNo:tasksNo, data=results, FUN=sd)$time
library("ggplot2")
ggplot(data=avg_results, aes(x=tasksNo, y=time, color=as.factor(threadsNo))) +
  geom_point() + 
  #geom_errorbar(aes(ymin=time-sd, ymax=time+sd), width=.1) + 
  ylab("Czas wykonania [ms]") +
  xlab("Liczba zadañ") +
  labs(color='Liczba w¹tków w pool') +
  scale_color_manual(breaks = c("1", "4", "8"), values=c("red", "blue", "green"))