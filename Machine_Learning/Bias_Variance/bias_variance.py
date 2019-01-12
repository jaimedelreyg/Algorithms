import pandas as p
import numpy as np
import matplotlib.pyplot as plt
import scipy.optimize as opt
from scipy.io import loadmat

#Data

data = loadmat('ex5data1')

y = data['y']
x_data = data['X']
x = np.hstack((np.ones(np.shape(x_data)),x_data))

xval = data['Xval']
xval = np.hstack((np.ones(np.shape(xval)),xval))
yval = data['yval']

xtest = data['Xtest']
xtest = np.hstack((np.ones(np.shape(xtest)),xtest))
ytest = data['ytest']

x_T = x.T
xval_T = xval.T
xtest_T = xtest.T

m = np.shape(x)[0]

def linear_regression_cost(theta,lmda,x,x_T,y):

    h = theta @ x_T
    h_T = np.matrix(h).T
    a = np.sum(np.square(h_T - y))
    cost = ((1/(2*m)) * a + lmda/(2*m))*(np.sum(np.square(theta[1:])))
    return cost

def linear_regression_gradient(theta,lmda,x,x_T,y):

    h = theta @ x_T
    h_T = np.matrix(h).T
    g= ((1/m) * ((h_T - y).T @ x).T) + (lmda/m * np.vstack((0,np.matrix(theta[1:]).T)))

    return np.asarray(g).ravel()

def fit(theta,l,x,x_T,y):

    res = opt.minimize(linear_regression_cost,x0=theta,args=(l,x,x_T,y),method = None,jac=linear_regression_gradient,options={'maxiter':5000})
    return res

## Grafica
res = fit([15,15],0,x,x_T,y)
h = np.vstack((np.ones(90),range(-50,40,1)))
h = np.array(res['x'] @ h)
h = h.tolist()

plt.figure()
plt.scatter(x_data,y)
plt.plot(range(-50,40,1),h,'-r')
plt.show()

#Learning curves

train = []
validation = []

for i in np.arange(1,13):

    res = fit([15, 15],0,x[:i,:],x_T[:,:i],y[:i,:])
    train.append(linear_regression_cost(res['x'],0,x[:i,:],x_T[:,:i],y[:i,:]))
    validation.append(linear_regression_cost(res['x'], 0, xval, xval_T, yval))

print(train)
print(validation)

plt.figure()
plt.plot(range(1,13,1),train,'-g')
plt.plot(range(1,13,1),validation,'-r')
plt.show()

#Polynomial regression



