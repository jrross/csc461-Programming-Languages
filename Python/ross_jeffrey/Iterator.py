class Iterator:
    '''
    :file: Iterator.py
    :author: Jeff Ross
    :details: Handles a Iterator class to use the iterator pattern.
    '''
    def __init__(self, coll):
        '''
        __init__
        constructor for the Iterator class to add an array to the claas
        :param coll: the array to be held
        '''
        self.collection = coll
        self.index = 0

    def __iter__(self):
        '''
        __iter__
        returns self to be used for iteration
        :return: self
        '''
        return self

    def __next__(self):
        '''
        __next__
        steps forward in the array and returns the value there. increments index
        :return: value forwards by one in current index
        '''
        if len(self.collection) < self.index + 1:
            raise StopIteration
        self.index = self.index + 1
        return self.index - 1, self.collection[self.index - 1]
