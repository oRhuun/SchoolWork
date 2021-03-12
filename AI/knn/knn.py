import numpy
from math import sqrt
from random import shuffle, randrange
import time

#retrieve the data to be used from a file and put it into a list
def getData(fileName): 
    data = []
    with open(fileName) as file:
        for line in file:
            line = line.strip() #remove \n
            data.append(line.split())
    return data
    
#calculate the Euclidean distance between two vectors
def euclideanDistance(r1, r2):
    distance = 0.0
    for i in range(1, len(r1)-1): #assumes 1st entry is label and last entry is name
        distance += (r1[i] - r2[i])**2
    return sqrt(distance)

#convert a column from strings to floats
def columnToFloat(dataset, column):
    for row in dataset:
        row[column] = float(row[column])
 
#convert a column (specifically the classification column) from strings to ints
def columnToInt(dataset, column):
    classValues = [row[column] for row in dataset]
    unique = set(classValues)
    classif = dict()
    for i, value in enumerate(unique):
        classif[value] = i
    for row in dataset:
        row[column] = classif[row[column]]
    return classif

#randomize data for before the cross validation split
def shuffleData(dataset):
    return shuffle(dataset)

#split the data into k folds for store all
def crossValidationSplit(dataset, kFolds):
    splitData = []
    copyData = list(dataset)
    foldSize = int(len(dataset) / kFolds)
    for _ in range(kFolds):
        fold = []
        while len(fold) < foldSize:
            fold.append(copyData.pop())
        splitData.append(fold)
    return splitData

#used to calculate accuracy of predictions
def accuracy(act, pred):
    correct = 0
    for i in range(len(act)):
        if act[i] == pred[i]:
            correct += 1
    return correct / float(len(act)) * 100.0

#get the n most similar neighbors
def getNeighbors(train, testRow, numNeighbors):
    distances = list()
    for trainRow in train:
        dist = euclideanDistance(testRow, trainRow)
        distances.append((trainRow, dist))
    distances.sort(key=lambda tup: tup[1]) #sort based on distance
    neighbors = list()
    for i in range(numNeighbors):
        neighbors.append(distances[i][0])
    return neighbors
 
#use neighbors to make a prediction
def predict(train, testRow, numNeighbors):
    neighbors = getNeighbors(train, testRow, numNeighbors)
    outputValues = [row[0] for row in neighbors]
    pred = max(set(outputValues), key=outputValues.count)
    return pred

#kNN Algorithm
def knn(train, test, numNeighbors):
    predictions = list()
    for row in test:
        output = predict(train, row, numNeighbors)
        predictions.append(output)
    return(predictions)

#Evaluate knn store all using the split from before
def evaluateSA(dataset, algorithm, kFolds, numNeighb):
    folds = crossValidationSplit(dataset, kFolds)
    accs = list()
    trainAccs = list()
    for fold in folds:
        trainSet = list(folds)
        trainSet.remove(fold)
        testSet = list()
        for row in fold:
            rowCopy = list(row)
            testSet.append(rowCopy)
            rowCopy[-1] = None
        trainSet = sum(trainSet, [])

        trainPred = algorithm(trainSet, trainSet, numNeighb)
        trainAct = [row[0] for row in trainSet]
        trainAcc = accuracy(trainAct, trainPred)
        trainAccs.append(trainAcc)

        predictions = algorithm(trainSet, testSet, numNeighb)
        actual = [row[0] for row in fold]
        acc = accuracy(actual, predictions)
        accs.append(acc)
    return (accs, trainAccs)

#Evaluate knn store errors using the split from before
def evaluateSE(dataset, algorithm, kFolds, numNeighb):
    folds = crossValidationSplit(dataset, kFolds)
    accs = list()
    trainAccs = list()
    for fold in folds:
        trainSetS = list(folds)
        trainSetS.remove(fold)
        testSet = list()
        for row in fold:
            rowCopy = list(row)
            testSet.append(rowCopy)
            rowCopy[-1] = None

        trainSetF = list()
        for nFold in trainSetS:
            for k in range(0, numNeighb):
                trainSetF.append(nFold.pop()) #store first k instances of each fold in the training set
        trainSetS = sum(trainSetS, [])
        for row in trainSetS:
            if(predict(trainSetS, row, numNeighb) != row[0]):
                trainSetF.append(row)

        trainPred = algorithm(trainSetF, trainSetF, numNeighb)
        trainAct = [row[0] for row in trainSetF]
        trainAcc = accuracy(trainAct, trainPred)
        trainAccs.append(trainAcc)

        predictions = algorithm(trainSetF, testSet, numNeighb)
        actual = [row[0] for row in fold]
        acc = accuracy(actual, predictions)
        accs.append(acc)
    return (accs, trainAccs)

#_main_
filename = 'labeled-examples'
dataset = getData(filename)
#shuffle the data as standard practice
shuffleData(dataset)
#convert data points to floats
for i in range(1, len(dataset[0])-1):
    columnToFloat(dataset, i)
# convert class column to integers
columnToInt(dataset, 0)

# evaluate knn
kFolds = 5
numNeighbors = 10
tStart = time.time()
accuracies = evaluateSA(dataset, knn, kFolds, numNeighbors)
tEnd = time.time()
print('KNN Store All:')
print('Train Accuracies: %s' % accuracies[1])
print('Average Train Accuracies: %.2f%%' % (sum(accuracies[1])/float(len(accuracies[1]))))
print('Test Accuracies: %s' % accuracies[0])
print('Average Test Accuracies: %.2f%%' % (sum(accuracies[0])/float(len(accuracies[0]))))
print('Time Taken: %.4f' % (tEnd-tStart) + ' seconds')

tStart = time.time()
accuracies = evaluateSE(dataset, knn, kFolds, numNeighbors)
tEnd = time.time()
print('\nKNN Store Error:')
print('Train Accuracies: %s' % accuracies[1])
print('Average Train Accuracies: %.2f%%' % (sum(accuracies[1])/float(len(accuracies[1]))))
print('Test Accuracies: %s' % accuracies[0])
print('Average Test Accuracies: %.2f%%' % (sum(accuracies[0])/float(len(accuracies[0]))))
print('Time Taken: %.4f' % (tEnd-tStart) + ' seconds')