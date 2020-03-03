using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace DiningPhilosophers
{
    public class Program
    {
        static int philisopherNumber = 15;
        static Task[] philosiphers;
        static SemaphoreSlim[] chopsticks;
        static SemaphoreSlim waiter;

        public static void Main(string[] args)
        {
            philosiphers = new Task[philisopherNumber];
            chopsticks = new SemaphoreSlim[philisopherNumber];
            waiter = new SemaphoreSlim(4);

            for (int i = 0; i < philisopherNumber; ++i)
            {
                philosiphers[i] = GenerateNewPhilosopher(i);
                chopsticks[i] = new SemaphoreSlim(1);
            }

            Task.WaitAll(philosiphers);
            Console.ReadLine();
        }

        private static Task GenerateNewPhilosopher(int philosipherIndex)
        {
            return Task.Run(() =>
            {
				for(int j =0;j<40;j++){var watch = System.Diagnostics.Stopwatch.StartNew();

                for (int i = 0; i < 1000; ++i)
                {
                    waiter.Wait();
                    var chopstick1 = GetChopstickIndex(philosipherIndex);
                    var chopstick2 = GetChopstickIndex(philosipherIndex + 1);

                    if (chopsticks[chopstick1].CurrentCount == 1 && chopsticks[chopstick2].CurrentCount == 1)
                    {
                        chopsticks[chopstick1].Wait();
                        chopsticks[chopstick2].Wait();
                    }
                    else
                    {
                        i -= 1;
                        waiter.Release();
                        continue;
                    }
                    waiter.Release();

                    Task.Delay(100);

                    chopsticks[chopstick1].Release();
                    chopsticks[chopstick2].Release();
					
                }
				watch.Stop();
					var elapsedMs = watch.ElapsedMilliseconds;
					Console.WriteLine("waiter 15 " +philosipherIndex + " " + elapsedMs);
									  }});
        }

        private static int GetChopstickIndex(int index)
        {
            return index % philisopherNumber;
        }
    }
}