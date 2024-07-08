import tensorflow as tf

class MLP:
    def __init__(self,hidden_layer_conf,num_output_nodes):
        self.hidden_layer_conf = hidden_layer_conf
        self.num_output_nodes