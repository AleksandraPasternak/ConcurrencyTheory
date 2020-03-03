// Used to watch amount of Promises executing in a single moment of time
let counter = 0;
let interval;
// Overall amount of operations
const numberOfOperations = 25;
// Arguments per operation
const listOfArguments = [];
// Delays per operation to fake async request
const listOfDelays = [];

// Fill delays in order to use the same array between all invocations
// Single delay is a value in milliseconds from 1000 to 10000
for (let i = 0; i < numberOfOperations; i++) {
  listOfArguments.push(i);
  listOfDelays.push(Math.ceil(Math.random() * 9) * 1000);
}

// Fake async: resolve an array through arbitrary delay
// Increase a counter in order to watch amount of Promises executed
const asyncOperation = index => {
  counter++;
  return new Promise(resolve =>
    setTimeout(() => {
      console.log('Operation performed:', index);
      counter--;
      resolve(index);
    }, listOfDelays[index]))
};

// Helper funtion to see the amount of running Promises each second
const watchCounter = () => {
  console.log('Promises running in the beginning:', counter);

  if (interval) {
    clearInterval(interval);
  }

  interval = setInterval(() => console.log('Promises running:', counter), 1000);
};